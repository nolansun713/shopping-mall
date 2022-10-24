package com.takeaway.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.takeaway.entity.RegexUtils;
import com.takeaway.entity.User;
import com.takeaway.mapper.UserSide.UserMapper;
import com.takeaway.service.IUserService;
import com.takeaway.service.ex.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


import static com.takeaway.entity.RedisConstants.CACHE_Code_KEY;
import static com.takeaway.entity.RedisConstants.LOGIN_CODE_TTL;

/**
 * @author kafka
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Value("${spring.mail.username}")
    private String SENDER;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public void addUser(User user) {
        // 1.判断邮箱格式
        String email=user.getEmail();
        if(RegexUtils.isEmailInvalid(email)){
            // 2.不符合抛出异常
            throw  new EmailFormatException("邮箱格式出错");
        }
        //根据用户名查询相关信息
        String username=user.getUsername();
        User result=userMapper.findByUsername(username);
       //查询邮箱是否被注册
        User CheckEmail=userMapper.findEmail(user.getEmail());
        if(CheckEmail!=null){
            throw new EmailSoleException("邮箱"+user.getEmail()+"已被注册");
        }
        //2.从redis 查询验证码
        String codeJson=stringRedisTemplate.opsForValue().get(CACHE_Code_KEY+email);
        String emailCode=user.getEmailCode();
        // 3.校验验证码
        if(emailCode == null ||!codeJson.equals(emailCode)){
            throw new CodeFalseException("验证码错误");
        }
        if(result!=null){
            throw new UsernameDuplicateException("用户名"+username+"已被占用");
        }
        Date date=new Date();
        //加密后的密码
        String salt= UUID.randomUUID().toString().toUpperCase();
        String md5Password=getMd5Password(user.getPassword(),salt);
        user.setPassword(md5Password);
        //盐值
        user.setSalt(salt);
        //数据
        user.setIsDelete(0);
        user.setCreatedUser(username);
        user.setCreatedTime(date);
        user.setModifiedUser(username);
        user.setModifiedTime(date);
        Integer rows=userMapper.addUser(user);
        if(rows!=1){
            throw new InsertException("注册用户异常");
        }
        stringRedisTemplate.delete(CACHE_Code_KEY+email);
    }

    /**
     * 用户登录
     *
     *
     * @param  user 密码
     * @return
     */
    @Override
    public User login(User user) {
        // 1判断邮箱格式
        if(RegexUtils.isEmailInvalid(user.getEmail())){
            // 2.不符合抛出异常
            throw  new EmailFormatException("邮箱格式出错");
        }
        //查询用户是否存在
        User result= userMapper.findByUsername(user.getUsername());
        //校验邮箱和用户名是否一致
        User CheckEmail=userMapper.findEmail(user.getEmail());
        if(CheckEmail==null){
            throw new EmailBoxAndUsernameException("邮箱和账号不一致");
        }
        if(!user.getEmail().equals(CheckEmail.getEmail())){
            throw new EmailBoxAndUsernameException("邮箱和账号不一致");
        }
        //2.从redis 查询验证码
        String codeJson=stringRedisTemplate.opsForValue().get(CACHE_Code_KEY+user.getEmail());
        String emailCode=user.getEmailCode();
        // 3.校验验证码
        if(emailCode == null ||!codeJson.equals(emailCode)){
            throw new CodeFalseException("验证码错误");
        }
        // 4.查询一致时，验证用户是否存在
       if(result==null){
           throw new UserNotFoundException("用户不存在");
       }
        //判断用户是否注销
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        //验证密码
        String md5Password=getMd5Password(user.getPassword(),result.getSalt());
        if(!md5Password.equals(result.getPassword())){
            throw new PasswordNotMathException("密码错误");
        }
        stringRedisTemplate.delete(CACHE_Code_KEY+user.getEmail());
        User row=new User();
        row.setUsername(result.getUsername());
        row.setUid(result.getUid());
        return row;
    }

    /**
     * 获取验证码
     *
     *
     * @param email
     */
    @Override
    public void GetCode(String email) {
        //1.验证邮箱
        if(RegexUtils.isEmailInvalid(email)){
            // 2.不符合抛出异常
            throw  new EmailFormatException("邮箱格式出错");
        }
        // 2.查询 redis是否有，有的话则抛出异常
        String codeJson=stringRedisTemplate.opsForValue().get(CACHE_Code_KEY+email);
        if(codeJson!=null){
            throw new CoolingTimeException("验证码发送时间冷却");
        }
        // 3.生成验证码
        String code = RandomUtil.randomNumbers(6);
        // 4.将验证码保存到 redis中
        stringRedisTemplate.opsForValue().set(CACHE_Code_KEY+email,code,LOGIN_CODE_TTL, TimeUnit.MINUTES);
        // 5.发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER);
        message.setTo(email);
        message.setSubject("验证码");
        message.setText("欢迎来到小苏商城,你的验证码是:"+code);
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常!", e);
        }

    }

    /**
     * 修改密码
     *
     * @param user
     */
    @Override
    public void updatePassword(User user) {
        // 1判断邮箱格式
        if(RegexUtils.isEmailInvalid(user.getEmail())){
            // 2.不符合抛出异常
            throw  new EmailFormatException("邮箱格式出错");
        }
        //查询用户信息
        User result= userMapper.findByUsername(user.getUsername());
        //校验邮箱和用户名是否一致
        User CheckEmail=userMapper.findEmail(user.getEmail());
        if(CheckEmail==null){
            throw new EmailBoxAndUsernameException("邮箱和账号不一致");
        }
        if(!user.getEmail().equals(CheckEmail.getEmail())){
            throw new EmailBoxAndUsernameException("邮箱和账号不一致");
        }
        //2.从redis 查询验证码
        String codeJson=stringRedisTemplate.opsForValue().get(CACHE_Code_KEY+user.getEmail());
        String emailCode=user.getEmailCode();
        // 3.校验验证码
        if(emailCode == null ||!codeJson.equals(emailCode)){
            throw new CodeFalseException("验证码错误");
        }
        // 4.查询一致时，验证用户是否存在
        //查询用户是否存在
        if(result==null){
            throw new UserNotFoundException("用户不存在");
        }
        String newPassword=getMd5Password(user.getPassword(),result.getSalt());
        String username=user.getUsername();
        Integer num=userMapper.updatePassword(newPassword,username,new Date(),username);
        if(num!=1){
            throw new UpdateException("修改密码失败");
        }
        stringRedisTemplate.delete(CACHE_Code_KEY+user.getEmail());
    }


    private String getMd5Password(String password,String salt){
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for(int i=0;i<3;i++){
            password= DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return  password;
    }
}

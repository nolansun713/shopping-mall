package com.takeaway.service;

import com.takeaway.entity.User;
import com.takeaway.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author kafka
 */
public interface IUserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    void addUser(User user);

    /**
     * 用户登录
     *
     * @param user 密码
     * @return
     */
    User login(User user);

    /**
     * 获取验证码
     */
    void GetCode(String email);

    /**
     * 修改密码
     * @param user
     */
    void updatePassword(User user);
}

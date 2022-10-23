package com.takeaway.mapper;

import com.takeaway.entity.Address;
import com.takeaway.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author kafka
 */
@Mapper
public interface UserMapper {
    /**
     * 添加用户
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 根据用户名查询信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 修改密码
     * @param username
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePassword(@Param("password") String password,
                           @Param("modifiedUser")String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime,
                           @Param("username") String username);

    /**
     * 根据用户名查询邮箱信息
     * @param username
     * @return
     */
    User  findEmail(String username);
}

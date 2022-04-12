package com.example.project.service;

import com.example.project.domain.User;

/**
 * 用户服务
 *
 * @author 杰瑞
 * @date 2022/03/27
 */
public interface UserService {

    /**
     * 找用户信息
     *
     * @param username 用户名
     * @return {@link User}
     */
    User find(String username);

    /**
     * 注册
     *
     * @param user 用户对象
     * @return boolean 注册成功与否
     */
    boolean register(User user);

}

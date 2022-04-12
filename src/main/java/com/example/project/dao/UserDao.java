package com.example.project.dao;

import com.example.project.domain.User;

/**
 * 用户Dao层
 *
 * @author 杰瑞
 * @date 2022/03/27
 */
public interface UserDao {

    /**
     * 找到
     *
     * @param username 用户名
     * @return {@link User}
     */
    User find(String username);
    /**
     * 保存
     *
     * @param user 用户
     */
    void save(User user);

    /**
     * 删除用户
     * @param userName
     */
    void delete(String userName);
}

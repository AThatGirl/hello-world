package com.cj.crm.settings.service;

import com.cj.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 根据用户账号密码查询
     *
     * @param map 地图
     * @return {@link User}
     */
    User queryUserByLoginActAndPwd(Map<String,Object> map);


    /**
     * 查询所有用户
     *
     * @return {@link List}<{@link User}>
     */
    List<User> queryAllUsers();
}

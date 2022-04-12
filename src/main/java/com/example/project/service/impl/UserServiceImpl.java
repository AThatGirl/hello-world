package com.example.project.service.impl;

import com.example.project.dao.UserDao;
import com.example.project.dao.impl.UserDaoImpl;
import com.example.project.domain.User;
import com.example.project.service.UserService;

/**
 * 用户服务层实现类
 *
 * @author 杰瑞
 * @date 2022/03/28
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public User find(String username) {
        return userDao.find(username);

    }

    @Override
    public boolean register(User user) {
        User user1 = userDao.find(user.getUsername());
        if (user1!=null){
            return false;
        }
        userDao.save(user);
        return true;
    }
}

package com.example.project.dao.impl;

import com.example.project.dao.UserDao;
import com.example.project.domain.User;
import com.example.project.util.JDBCUtils;
import com.example.project.util.PasswordUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.*;
import java.util.List;


/**
 * 用户Dao实现类
 *
 * @author 杰瑞
 * @date 2022/03/27
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDatasource());

    /**
     * 通过用户名找
     *
     * @param username 用户名
     * @return {@link User}
     */
    @Override
    public User find(String username) {
        String sql="select * from user where username='"+username+"'";
        User user=new User();
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        if (!list.isEmpty()){
            user=list.get(0);
            return user;
        }
            return null;
    }

    /**
     * 保存到数据库
     *
     * @param user 用户
     */
    @Override
    public void save(User user) {
        if (user==null){
            return;
        }
        String password = PasswordUtils.encryptPassword(user.getPassword());
        String sql="insert into user(username,password,email,birthday) values('"+user.getUsername()+"',"
                +"'"+password+"',"+
                "'"+user.getEmail()+"'," +
                "'"+user.getDate()+"')";
        jdbcTemplate.update(sql);
    }

    @Override
    public void delete(String userName) {

    }
}

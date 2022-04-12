package com.example.project.servlet;

import com.example.project.domain.ResultMessage;
import com.example.project.domain.User;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;
import com.example.project.util.JDBCUtils;
import com.example.project.util.PasswordUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * 登录servlet
 *
 * @author 杰瑞
 * @date 2022/03/27
 */
@WebServlet(name = "LoginServlet", value = "/TomShop/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取登录的账号和密码
        String username = request.getParameter("username");
        String password = PasswordUtils.encryptPassword(request.getParameter("password"));


        //创建转换json的对象
        ObjectMapper objectMapper=new ObjectMapper();
        //创建结果信息对象
        ResultMessage message=new ResultMessage();
        //创建user服务层相关对象
        UserService userService=new UserServiceImpl();
        User user = userService.find(username);
        if (username!=null&&password!=null&&user!=null&&user.getPassword().equals(password)){
            //登陆成功
            message.setFlag(true);
            message.setError_message(username);
            //响应数据
            response.setContentType("application/json;charset=utf-8");
            String json = objectMapper.writeValueAsString(message);
            response.getWriter().write(json);
        }else{
            message.setFlag(false);
            message.setError_message("用户名或密码错误");
            response.setContentType("application/json;charset=utf-8");
            String json = objectMapper.writeValueAsString(message);
            //给客户端写回数据
            response.getWriter().write(json);
        }

    }
}

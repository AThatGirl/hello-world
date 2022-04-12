package com.example.project.servlet;

import com.example.project.domain.ResultMessage;
import com.example.project.domain.StaticVariable;
import com.example.project.domain.User;
import com.example.project.service.UserService;
import com.example.project.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/TomShop/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取输入的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String date = request.getParameter("birthday");

        //创建user相关对象
        UserService userService=new UserServiceImpl();
        User user1 = userService.find(username);
        User user2=new User();
        //创建结果信息对象
        ResultMessage resultMessage=new ResultMessage();
        //创建日期格式化对象
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        //创建序列化json对象
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            //判断数据库是否有输入的用户名
            if (user1==null) {
                //判断输入是否为空
                if (!StringUtils.isBlank(username) &&!StringUtils.isBlank(password)&&!StringUtils.isBlank(email)&&!StringUtils.isBlank(date)) {
                    user2.setUsername(username);
                    user2.setPassword(password);
                    user2.setEmail(email);
                    user2.setDate(date);
                    boolean register = userService.register(user2);
                    //将信息填入结果信息对象
                    if (register==true) {
                        resultMessage.setSituation(StaticVariable.REGISTER_SUCCESS);
                        resultMessage.setError_message("");
                    }
                }else {
                    //将信息填入结果信息对象
                    resultMessage.setSituation(StaticVariable.INPUT_NULL);
                    resultMessage.setError_message("信息不完整");
                }
            }else {
                //将信息填入结果信息对象
                resultMessage.setSituation(StaticVariable.USER_EXIST);
                resultMessage.setError_message("该用户名已经存在");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        String json=objectMapper.writeValueAsString(resultMessage);
        response.getWriter().write(json);

    }
}

package com.example.project.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试servlet
 *
 * @author 杰瑞
 * @date 2022/03/28
 */
@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String,String> map=new HashMap<>();
        if (username!=null&&password!=null){
            map.put("flag","false");
            map.put("res","不能为空");

            ObjectMapper objectMapper=new ObjectMapper();
            String json = objectMapper.writeValueAsString(map);
            response.getWriter().write(json);
        }else {
            map.put("flag","true");
            map.put("res","登陆成功");

            ObjectMapper objectMapper=new ObjectMapper();
            String json = objectMapper.writeValueAsString(map);
            response.getWriter().write(json);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}

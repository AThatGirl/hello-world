package com.example.project.servlet;

import com.example.project.domain.Product;
import com.example.project.service.ShoppingCartService;
import com.example.project.service.impl.ShoppingCartServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 购物车servlet
 *
 * @author 杰瑞
 * @date 2022/04/03
 */
@WebServlet("/TomShop/shoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        String name=request.getParameter("name");
        String src = request.getParameter("src");
        String priceStr = request.getParameter("price");
        String[] price1 = priceStr.split("￥");
        int price= Integer.parseInt(price1[1]);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
        String format = sdf.format(date);
        //定义对象保存数据
        ShoppingCartService service=new ShoppingCartServiceImpl();
        Product product=new Product();
        //将数据写入Product对象
        product.setImage(src);
        product.setName(name);
        product.setPrice(price);
        product.setCount(1);
        product.setDate(format);
        //将数据保存到数据库
        service.save(product);
        response.sendRedirect("shoppingCart.jsp");
        /*
        product = service.find(product.getName());
        //序列化json对象
        ObjectMapper objectMapper=new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);
        System.out.println(json);
        //将产品信息返回给客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
        */
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}

package com.example.project.servlet;

import com.example.project.dao.ShoppingCartDao;
import com.example.project.dao.impl.ShoppingCartDaoImpl;
import com.example.project.service.ShoppingCartService;
import com.example.project.service.impl.ShoppingCartServiceImpl;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author 杰瑞
 * @date 2022/04/11
 */
@WebServlet("/TomShop/myself/removeProductServlet")
public class RemoveProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取产品名称
        String name = request.getParameter("name");
        //创建购物车service对象
        ShoppingCartService shoppingCartService=new ShoppingCartServiceImpl();
        if (!StringUtils.isBlank(name)){
            shoppingCartService.delete(name);
        }
        response.getWriter().write("<script>alert(\"移除成功\")</script>");
        response.sendRedirect("shoppingCart.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}

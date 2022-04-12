package com.example.project.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 过滤器,用来检测用户是否登录
 *
 * @author 杰瑞
 * @date 2022/03/27
 */
@WebFilter(filterName = "UserFilter",urlPatterns = "/*")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {



    chain.doFilter(request, response);
    }
}

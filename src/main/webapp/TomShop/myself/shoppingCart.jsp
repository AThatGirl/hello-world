<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="com.example.project.util.JDBCUtils" %>
<%@ page import="org.springframework.jdbc.core.BeanPropertyRowMapper" %>
<%@ page import="com.example.project.domain.Product" %>
<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: 杰瑞
  Date: 2022/4/11
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>

    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            border: 1px solid rgba(0,0,0,0.3);
        }
        li{
            float: left;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 70px;
            text-decoration: none;
            height: 50px;
            width: 100px;
        }
        .head{
            color: white;
        }
        img{
            height: 80px;
            width:60px ;
        }

    </style>
    <script src="../../lib/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#remove").click(function () {
                let name = $("#remove").val();
                location.href="removeProductServlet?name="+name;
            });
            $("#pay").click(function () {

            });
        });


    </script>
</head>
<body>
    <ul style="list-style-type: none;margin: 0;padding: 0;overflow: hidden;background-color: #f12b2b;height: 50px">
        <li class="head">图片</li>
        <li class="head">名称</li>
        <li class="head">价格</li>
        <li class="head">购买数目</li>
        <li class="head">购买日期</li>
        <li class="head">操作</li>
    </ul>
    <%
        int sumPrice=0;
        String sql="select * from shoppingCart";
        JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDatasource());
        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        for (Product product : products) {
            sumPrice+=product.getPrice()* product.getCount();
    %>
    <ul>
        <li><img src="<%=product.getImage()%>" alt="商品图片"></li>
        <li><%=product.getName()%></li>
        <li><%=product.getPrice()*product.getCount()%></li>
        <li><%=product.getCount()%></li>
        <li><%=product.getDate()%></li>
        <li><button type="button" id="remove" value="<%=product.getName()%>">移除</button><button type="button" id="pay">付款</button></li>
    </ul>
    <%}%>
    <button type="button" id="payAll">一键支付</button>

</body>
</html>

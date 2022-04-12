package com.example.project.dao.impl;

import com.example.project.dao.ShoppingCartDao;
import com.example.project.domain.Product;
import com.example.project.domain.ShoppingCart;
import com.example.project.util.JDBCUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 购物车Dao实现类
 *
 * @author 杰瑞
 * @date 2022/04/03
 */
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDatasource());

    @Override
    public Product find(String name) {
        String sql="select * from shoppingcart where name='"+name+"'";
        Product product=new Product();
        List<Product> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        if (!list.isEmpty()){
            product=list.get(0);
            return product;
        }
        return null;
    }

    @Override
    public void save(Product product) {
        if (find(product.getName())!=null){
            update(product);
            return;
        }
        String sql="insert into shoppingcart values('"+
                product.getName()+"','"+
                product.getImage()+"',"+
                product.getPrice()+","+
                product.getCount()+",'"+
                product.getDate()+"')";
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    @Override
    public void update(Product product) {
        String sql="update shoppingcart set count=count+1 where name='"+product.getName()+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void delete(String productName) {
        String sql="delete from shoppingcart where name='"+productName+"'";
        jdbcTemplate.update(sql);
    }

}

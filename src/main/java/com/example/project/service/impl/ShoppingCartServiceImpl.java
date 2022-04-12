package com.example.project.service.impl;

import com.example.project.dao.ShoppingCartDao;
import com.example.project.dao.impl.ShoppingCartDaoImpl;
import com.example.project.domain.Product;
import com.example.project.service.ShoppingCartService;

/**
 * 购物车Service实现类
 *
 * @author 杰瑞
 * @date 2022/04/03
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartDao shoppingCartDao=new ShoppingCartDaoImpl();
    @Override
    public Product find(String name) {
       return shoppingCartDao.find(name);
    }

    @Override
    public void save(Product product) {
        shoppingCartDao.save(product);
    }

    @Override
    public void update(Product product) {
        shoppingCartDao.update(product);
    }

    @Override
    public void delete(String productName) {
        shoppingCartDao.delete(productName);
    }
}

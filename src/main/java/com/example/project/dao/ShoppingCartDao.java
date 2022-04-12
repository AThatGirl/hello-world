package com.example.project.dao;

import com.example.project.domain.Product;
import com.example.project.domain.ShoppingCart;

/**
 * 购物车Dao层
 *
 * @author 杰瑞
 * @date 2022/04/03
 */
public interface ShoppingCartDao {
    /**
     * 找到
     *
     * @param name 根据名字找到产品
     * @return {@link Product}
     */
    Product find(String name);

    /**
     * 保存
     *
     * @param product 产品
     */
    void save(Product product);

    /**
     * 更新产品内容
     *
     * @param product 产品
     */
    void update(Product product);

    /**
     * 删除购物车产品
     *
     * @param productName
     */
    void delete(String productName);
}

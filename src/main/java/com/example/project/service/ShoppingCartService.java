package com.example.project.service;

import com.example.project.domain.Product;

/**
 * 购物车服务
 *
 * @author 杰瑞
 * @date 2022/04/03
 */
public interface ShoppingCartService {
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

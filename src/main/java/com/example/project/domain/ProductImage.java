package com.example.project.domain;

/**
 * 产品图片
 *
 * @author 杰瑞
 * @date 2022/03/29
 */
public class ProductImage {
    private int id;
    private String type;
    private Product product;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "type='" + type + '\'' +
                ", product=" + product +
                ", id=" + id +
                '}';
    }
}

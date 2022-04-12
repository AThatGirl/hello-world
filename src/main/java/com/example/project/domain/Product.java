package com.example.project.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品
 *
 * @author 杰瑞
 * @date 2022/03/29
 */
public class Product {
    private String name;
    private String image;
    private int price;
    private int count;
    private String date;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", date=" + date +
                '}';
    }
}

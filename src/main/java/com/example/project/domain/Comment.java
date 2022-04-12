package com.example.project.domain;

import java.util.Date;

/**
 * 评论
 *
 * @author 杰瑞
 * @date 2022/03/29
 */
public class Comment {
    private int id;
    private String content;
    private User user;
    private Product product;
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", product=" + product +
                ", createDate=" + createDate +
                '}';
    }
}

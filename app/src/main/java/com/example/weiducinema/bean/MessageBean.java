package com.example.weiducinema.bean;

/**
 * Created by 邵新轩(轩少)
 * function:
 * on 2019/1/27
 */
public class MessageBean {
    private String title;
    private int price;

    public MessageBean(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

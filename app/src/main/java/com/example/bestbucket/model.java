package com.example.bestbucket;

import android.view.View;

public class model {
    private String brand;
    private String desc;
    private String img;
    private String name;
    private String  price;
    private String  link;

    public model(){}
    public model(String name,String img, String desc, String price, String brand,String  link) {
        this.name= name;
        this.img = img;
        this.desc = desc;
        this.price = price;
        this.brand = brand;
        this.link = link;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

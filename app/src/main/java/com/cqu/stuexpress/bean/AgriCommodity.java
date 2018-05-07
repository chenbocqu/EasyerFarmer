package com.cqu.stuexpress.bean;

import java.util.ArrayList;

/**
 * 农料农具，商品，可以交易的，农场主需要购买的
 */

public class AgriCommodity {

    private String imgUrl;//头像URL
    private String title; // 标题

    private String desc;//描述
    private int stock; // 有多少库存
    private int volume; // 成交量
    private double price; // 单价

    private String city;// 发货城市
    private String speed; // 发货速度

    private ArrayList<String> imgs; // 图片集，最多9张

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public ArrayList<String> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<String> imgs) {
        this.imgs = imgs;
    }
}

package com.example.avishkar_2021.Models;

import java.util.Date;

public class ItemModel {
    private int item_id;
    private String seller, name, image;
    private Date date;

    public ItemModel(int item_id, String seller, Date date) {
        this.item_id = item_id;
        this.seller = seller;
        this.date = date;
    }

    public ItemModel(int price, String name, String image) {
        this.name = name;
        this.image = image;
    }

    public ItemModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

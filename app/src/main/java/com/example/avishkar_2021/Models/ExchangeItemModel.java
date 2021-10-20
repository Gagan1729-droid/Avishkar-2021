package com.example.avishkar_2021.Models;

public class ExchangeItemModel {
    String item_name, city, description, item_id, items_exchange, item_images, seller_name;
    long timestamp;

    public ExchangeItemModel() {
    }

    public ExchangeItemModel(String item_name, String city, String description, String item_id, String items_exchange, String item_images, String seller_name) {
        this.item_name = item_name;
        this.city = city;
        this.description = description;
        this.item_id = item_id;
        this.items_exchange = items_exchange;
        this.item_images = item_images;
        this.seller_name = seller_name;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItems_exchange() {
        return items_exchange;
    }

    public void setItems_exchange(String items_exchange) {
        this.items_exchange = items_exchange;
    }

    public String getItem_images() {
        return item_images;
    }

    public void setItem_images(String item_images) {
        this.item_images = item_images;
    }
}

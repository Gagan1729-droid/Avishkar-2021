package com.example.avishkar_2021.Models;

public class UserModel {
    private String name, UserId;
    private boolean isNGO;

    public UserModel() {
    }

    public UserModel(String name, String userId, boolean isNGO) {
        this.name = name;
        UserId = userId;
        this.isNGO = isNGO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public boolean isNGO() {
        return isNGO;
    }

    public void setNGO(boolean NGO) {
        isNGO = NGO;
    }
}

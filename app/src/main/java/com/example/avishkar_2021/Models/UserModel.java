package com.example.avishkar_2021.Models;

public class UserModel {
    private String name, userId;
    private boolean isNGO;
    private long timestamp;

    public UserModel() {
    }

    public UserModel(String name, String userId, boolean isNGO, long timestamp) {
        this.name = name;
        this.userId = userId;
        this.isNGO = isNGO;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        userId = userId;
    }

    public boolean isNGO() {
        return isNGO;
    }

    public void setNGO(boolean NGO) {
        isNGO = NGO;
    }
}

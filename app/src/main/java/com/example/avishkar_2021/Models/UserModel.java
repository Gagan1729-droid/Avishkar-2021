package com.example.avishkar_2021.Models;

public class UserModel {
    private String name, userId, location, ngo_name;
    private boolean isNGO;
    private long timestamp;
    private double latitude, longitude;

    public UserModel() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserModel(String name, String userId, boolean isNGO, long timestamp) {
        this.name = name;
        this.userId = userId;
        this.isNGO = isNGO;
        this.timestamp = timestamp;
        this.location = null;
    }

    public UserModel(String name, String userId, boolean isNGO, long timestamp, double latitude, double longitude) {
        this.name = name;
        this.userId = userId;
        this.isNGO = isNGO;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserModel(String name, String userId, String location, String ngo_name, boolean isNGO, long timestamp, double latitude, double longitude) {
        this.name = name;
        this.userId = userId;
        this.ngo_name = ngo_name;
        this.isNGO = isNGO;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

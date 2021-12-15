package com.example.avishkar_2021.Models;

public class MessagesModel {
    String message, id;
    long timestamp;

    public MessagesModel(){ }
    public MessagesModel(String message, String id, long timestamp) {
        this.message = message;
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

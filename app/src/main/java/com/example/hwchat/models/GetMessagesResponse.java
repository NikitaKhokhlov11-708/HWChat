package com.example.hwchat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMessagesResponse {

    @SerializedName("items")
    @Expose
    private List<Message> items = null;
    @SerializedName("first")
    @Expose
    private Integer first;

    public List<Message> getItems() {
        return items;
    }

    public void setItems(List<Message> items) {
        this.items = items;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

}

class Item {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

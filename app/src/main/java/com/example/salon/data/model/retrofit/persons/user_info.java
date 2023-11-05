package com.example.salon.data.model.retrofit.persons;

import com.google.gson.annotations.SerializedName;

public class user_info {

//    @SerializedName("image")
//    private String image;

    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("password")
    private String password;

    public user_info(String username, String name, String phone, String password) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

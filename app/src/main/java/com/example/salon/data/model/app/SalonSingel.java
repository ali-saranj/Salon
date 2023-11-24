package com.example.salon.data.model.app;

import com.example.salon.data.model.retrofit.getsalon.Response;

import org.jetbrains.annotations.NotNull;

public class SalonSingel {
    String title, description, address, gps, phone,image, location;
    float rant;

    public SalonSingel(@NotNull Response body) {
        title = body.getName();
        description = body.getDescription();
        address = body.getAddress();
        rant = 3;
        gps = body.getLocation();
        phone = body.getPhone();
        image = body.getImages().get(0).getImage();
        location = body.getLocation();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRant() {
        return rant;
    }

    public void setRant(float rant) {
        this.rant = rant;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

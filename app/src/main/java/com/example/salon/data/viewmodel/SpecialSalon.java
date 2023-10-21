package com.example.salon.data.viewmodel;

import com.example.salon.data.model.retrofit.home.SpecialOffersSalonItem;

import org.jetbrains.annotations.NotNull;

public class SpecialSalon {
    private int id;
    private String title, discretion,image;

    public SpecialSalon( SpecialOffersSalonItem specialSalon) {
        id = specialSalon.getId();
        title = specialSalon.getName();
        discretion = specialSalon.getDescription();
        image = specialSalon.getImages().get(0).getImage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscretion() {
        return discretion;
    }

    public void setDiscretion(String discretion) {
        this.discretion = discretion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

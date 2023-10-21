package com.example.salon.data.viewmodel;

import com.example.salon.data.model.retrofit.home.SalonItem;

import org.jetbrains.annotations.Nullable;

public class SalonCard {
    int id;
    String title, image;

    public SalonCard(SalonItem salonItem) {
        id = salonItem.getId();
        title = salonItem.getName();
        image = salonItem.getImages().get(0).getImage();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

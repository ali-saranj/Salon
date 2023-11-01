package com.example.salon.data.viewmodel;

import com.example.salon.data.model.retrofit.home.SalonItem;
import com.example.salon.data.model.retrofit.search.ResponseItem;

import org.jetbrains.annotations.Nullable;

public class SalonCard {
    int id;
    String title, image,description;

    public SalonCard(SalonItem salonItem) {
        id = salonItem.getId();
        title = salonItem.getName();
        image = salonItem.getImages().get(0).getImage();
        description = salonItem.getDescription();
    }

    public SalonCard(ResponseItem responseItem) {
        id = responseItem.getId();
        title = responseItem.getName();
        image = responseItem.getImages().get(0).getImage();
        description = responseItem.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

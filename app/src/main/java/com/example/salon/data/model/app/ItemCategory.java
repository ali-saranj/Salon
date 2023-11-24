package com.example.salon.data.model.app;

import com.example.salon.data.model.retrofit.home.CategorysItem;
import com.example.salon.data.model.retrofit.home.SalonItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemCategory {
    int id;
    String title,image;
    List<SalonItem> salonItems;

    public ItemCategory(CategorysItem categorysItem) {
        id = categorysItem.getId();
        title = categorysItem.getName();
        image = categorysItem.getImage();
        salonItems = categorysItem.getSalon();
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

    public List<SalonItem> getSalonItems() {
        return salonItems;
    }

    public void setSalonItems(List<SalonItem> salonItems) {
        this.salonItems = salonItems;
    }
}

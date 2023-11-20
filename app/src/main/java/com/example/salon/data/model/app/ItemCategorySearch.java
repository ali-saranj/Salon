package com.example.salon.data.model.app;

import com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem;

public class ItemCategorySearch {
    int id;
    String title;

    public ItemCategorySearch(ResponseItem responseItem) {
        id = 0;
        title = responseItem.getName();
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
}

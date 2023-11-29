package com.example.salon.data.model.app;

import static com.example.tools.ToolsKt.StringSpace;

import android.annotation.SuppressLint;

import com.example.salon.data.model.retrofit.home.SalonItem;
import com.example.salon.data.model.retrofit.search.ResponseItem;
import com.example.salon.ui.activity.MainActivity;

import org.jetbrains.annotations.Nullable;

public class SalonCard {
    int id;
    String spase;
    String title, image,description,location;

    @SuppressLint("DefaultLocale")
    public SalonCard(SalonItem salonItem) {
        id = salonItem.getId();
        title = salonItem.getName();
        image = salonItem.getImages().get(0).getImage();
        description = salonItem.getDescription();
        if(!(salonItem.getLongitude().isEmpty()||salonItem.getLatitude().isEmpty()))
            spase = StringSpace(Double.parseDouble(salonItem.getLatitude()),Double.parseDouble(salonItem.getLongitude()),MainActivity.Latitude,MainActivity.Longitude);
        else
            spase = null;
        location = salonItem.getLocation();
    }
    @SuppressLint("DefaultLocale")
    public SalonCard(ResponseItem responseItem) {
        id = responseItem.getId();
        title = responseItem.getName();
        image = responseItem.getImages().get(0).getImage();
        description = responseItem.getDescription();
        if(!(responseItem.getLongitude().isEmpty()||responseItem.getLatitude().isEmpty()))
            spase = StringSpace(Double.parseDouble(responseItem.getLatitude()),Double.parseDouble(responseItem.getLongitude()),MainActivity.Latitude,MainActivity.Longitude);
        else
            spase = null;
        location = responseItem.getLocation();
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

    public String getSpase() {
        return spase;
    }

    public void setSpase(String spase) {
        this.spase = spase;
    }


    public double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0; // Earth radius in kilometers

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

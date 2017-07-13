package com.example.mushi.advertapp;


import java.util.List;

/**
 * Created by mushi on 6/6/17.
 */

class Advertisment {
    int id;
    String name;
    String price;
    String photoId;
    String location;
    String description;
    String date;

    Advertisment(int id,String name, String price, String photoId,String location,String description,String date) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.photoId = photoId;
        this.location=location;
        this.description=description;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



}
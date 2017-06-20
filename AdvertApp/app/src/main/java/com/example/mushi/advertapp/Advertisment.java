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

    Advertisment(int id,String name, String price, String photoId,String location) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.photoId = photoId;
        this.location=location;
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
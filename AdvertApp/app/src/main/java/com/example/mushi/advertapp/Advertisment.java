package com.example.mushi.advertapp;


import java.util.List;

/**
 * Created by mushi on 6/6/17.
 */

class Advertisment {
    String name;
    String price;
    int photoId;
    String location;

    Advertisment(String name, String age, int photoId,String location) {
        this.name = name;
        this.price = age;
        this.photoId = photoId;
        this.location=location;
    }


    private List<Advertisment> advertisment;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {

        advertisment.add(new Advertisment("Emma Wilson", "23 years old", R.drawable.ic_airport_shuttle_black_24dp,"karachi"));
        advertisment.add(new Advertisment("Lavery Maiss", "25 years old", R.drawable.ic_color_lens_black_24dp,"Lahore"));

    }
}
package com.katoch.restaurantfinder.data;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;
import java.util.ArrayList;

public class BusinessDetail implements Serializable
{
    @JsonGetter("photos")
    public ArrayList<String> getPhotos() {
        return this.photos;
    }
    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
    ArrayList<String> photos;

}

package com.katoch.restaurantfinder.view;

import com.katoch.restaurantfinder.data.BusinessCategory;

import java.util.ArrayList;

public interface IView {
    void setBusinessesInfo(ArrayList<BusinessCategory> dataSet);
    void setBusinessPhoto(ArrayList<String> photos);
    void onFailure(String errorStr);
}

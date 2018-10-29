package com.katoch.restaurantfinder.view;

import com.katoch.restaurantfinder.model.Business;

import java.util.ArrayList;

public interface IView {
    void showProgress();
    void setBusinessesInfo(ArrayList<Business> dataSet);
    void setBusinessPhoto(ArrayList<String> photos);
    void onFailure(String errorStr);
}

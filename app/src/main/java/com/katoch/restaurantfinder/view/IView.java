package com.katoch.restaurantfinder.view;

import com.katoch.restaurantfinder.model.Business;

import java.util.ArrayList;

public interface IView {
    void showProgress();
    void setDataToRecyclerView(ArrayList<Business> dataSet);
    void onFailure(String errorStr);
}

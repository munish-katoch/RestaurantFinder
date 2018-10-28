package com.katoch.restaurantfinder.presenter;

import android.app.Activity;
import android.content.Context;

public class PresenterFactory {

    public static IPresenter getInstance(Context context) {
        // At this point we just support Yelp Search.
        return new YelpPresenter(context);
    }
}

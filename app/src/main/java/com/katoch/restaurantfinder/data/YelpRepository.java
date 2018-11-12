package com.katoch.restaurantfinder.data;

import com.katoch.restaurantfinder.di.WebModule;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

//ToDo No retrofit function.
import retrofit2.Call;
import retrofit2.Callback;


public class YelpRepository {
    private static final String TAG = "YelpRepository";

    @Inject
    public Webservice mWebservice = (new WebModule()).getWebService();


    public void getBusinessesSortByCategory(String latitude, String longitude, Callback<YelpSearchResponse> callback) {

        Map<String, String> params = new HashMap<>();
        params.put("term", "restaurants");
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("limit", "50");

        Call<YelpSearchResponse> call = mWebservice.getBusinessSearch(params);
        call.enqueue(callback);
    }

    public void getBusinessPhotos(String businessId, Callback<BusinessDetail> callback) {
        Call<BusinessDetail> call = mWebservice.getBusinessPhotos(businessId);
        call.enqueue(callback);
    }

    public void cancelOngoingCommand() {
        //ToDo Need to check?
//        if (mWebservice != null) {
//            mWebservice.dispatcher().cancelAll();
//        }
    }
}
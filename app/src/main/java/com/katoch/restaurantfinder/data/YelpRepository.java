package com.katoch.restaurantfinder.data;

import com.katoch.restaurantfinder.di.WebModule;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

//ToDo No retrofit function.
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class YelpRepository {
    private static final String TAG = "YelpRepository";

    @Inject
    public Webservice mWebservice;


    public Single<YelpSearchResponse> getBusinessesSortByCategory(String latitude, String longitude) {
        Map<String, String> params = new HashMap<>();
        params.put("term", "restaurants");
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("limit", "50");

        return mWebservice.getBusinessSearch(params);
    }

    public Single<BusinessDetail> getBusinessPhotos(String businessId) {
        return mWebservice.getBusinessPhotos(businessId);
    }

    public void cancelOngoingCommand() {
        //ToDo Need to check?
//        if (mWebservice != null) {
//            mWebservice.dispatcher().cancelAll();
//        }
    }
}
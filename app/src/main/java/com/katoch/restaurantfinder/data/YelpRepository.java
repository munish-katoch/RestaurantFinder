package com.katoch.restaurantfinder.data;

import com.katoch.restaurantfinder.di.WebModule;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

//ToDo No retrofit function.
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class YelpRepository {
    private static final String TAG = "YelpRepository";

    @Inject
    public Webservice mWebservice = (new WebModule()).getWebService();


    public Observable<YelpSearchResponse> getBusinessesSortByCategory(String latitude, String longitude) {

        Map<String, String> params = new HashMap<>();
        params.put("term", "restaurants");
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("limit", "50");

        Callback<YelpSearchResponse> callback = new Callback <YelpSearchResponse>() {
            @Override
            public void onResponse(Call<YelpSearchResponse> call, Response<YelpSearchResponse> response) {
                YelpSearchResponse response1 = response.body();
                //mActivityView.setBusinessesInfo(response1.getBusinessesSortByCategory());
            }
            @Override
            public void onFailure(Call<YelpSearchResponse> call, Throwable t) {
                //mActivityView.onFailure(mContext.getString(com.katoch.restaurantfinder.R.string.error_failure_yelp_response) + t.getMessage());
            }
        };

        Call<YelpSearchResponse> call = mWebservice.getBusinessSearch(params);
        call.enqueue(callback);
        //return Observable.fromCallable(callback);
        return null;
    }

    public Observable<BusinessDetail> getBusinessPhotos(String businessId, Callback<BusinessDetail> callback) {
        Call<BusinessDetail> call = mWebservice.getBusinessPhotos(businessId);
        call.enqueue(callback);
        return null;
    }

    public void cancelOngoingCommand() {
        //ToDo Need to check?
//        if (mWebservice != null) {
//            mWebservice.dispatcher().cancelAll();
//        }
    }
}
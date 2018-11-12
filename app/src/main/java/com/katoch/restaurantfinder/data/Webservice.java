package com.katoch.restaurantfinder.data;

import com.katoch.restaurantfinder.BuildConfig;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Webservice {

    public static final String BASE_URL = "https://api.yelp.com";
    public static final String API_KEY = BuildConfig.API_KEY;

    @GET("/v3/businesses/search")
    Call<YelpSearchResponse> getBusinessSearch(@QueryMap Map<String, String> params);

    @GET("/v3/businesses/{id}")
    Call<BusinessDetail> getBusinessPhotos(@Path("id") String businessId);
}

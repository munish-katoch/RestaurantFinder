package com.katoch.restaurantfinder.model;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface YelpWebservice {

    @GET("/v3/businesses/search")
    Call<YelpSearchResponse> getBusinessSearch(@QueryMap Map<String, String> params);

    @GET("/v3/businesses/{id}")
    Call<BusinessDetail> getBusinessPhotos(@Path("id") String businessId);
}

package com.katoch.restaurantfinder.model;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class YelpRepository {
    private static Retrofit retrofit;
    private static final String TAG = "YelpRepository";
    private static final String BASE_URL = "https://api.yelp.com";
    private static final String API_KEY = "R2Sc7Q-gezuwakeW4a6jeImAGUK2YLzq6cfV6xyXA_g9hbFfblINtgYRwxscFJQCUNNI22aQik756ZhbjGNLzgqmEU8XEycDvKATbokqLEXsxYzMyquPq5N3tmXSW3Yx";



    public static Retrofit getRetrofitInstance() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Callback<YelpSearchResponse> callback = new Callback <YelpSearchResponse>() {
            @Override
            public void onResponse(Call<YelpSearchResponse> call, Response<YelpSearchResponse> response) {
                YelpSearchResponse response1 = response.body();
                Log.d(TAG,"Total =" + response1.total);
                // Update UI text with the Business object.
            }
            @Override
            public void onFailure(Call<YelpSearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
            }
        };
        YelpWebservice webservice = retrofit.create(YelpWebservice.class);
        Map<String, String> params = new HashMap<>();

        // general params
        params.put("term", "restaurants");
        params.put("latitude", "40.581140");
        params.put("longitude", "-111.914184");

        Call<YelpSearchResponse> call = webservice.getBusinessSearch(params);
        call.enqueue(callback);
        return retrofit;
    }
}
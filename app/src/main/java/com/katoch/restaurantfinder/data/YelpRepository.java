package com.katoch.restaurantfinder.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpRepository {
    private static final String TAG = "YelpRepository";
    private static final String BASE_URL = "https://api.yelp.com";
    private static final String API_KEY = "R2Sc7Q-gezuwakeW4a6jeImAGUK2YLzq6cfV6xyXA_g9hbFfblINtgYRwxscFJQCUNNI22aQik756ZhbjGNLzgqmEU8XEycDvKATbokqLEXsxYzMyquPq5N3tmXSW3Yx";

    private Retrofit mRetrofit;
    private Call mCurrentCall;
    private OkHttpClient mClient;

    public YelpRepository() {
        try {
            mRetrofit = getRetrofitInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Retrofit getRetrofitInstance() throws IOException {
        mClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        if (mRetrofit == null) {
            mRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(mClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return mRetrofit;
    }

    public void getBusinessesSortByCategory(String latitude, String longitude, Callback<YelpSearchResponse> callback) {
        if (mRetrofit == null) {
            try {
                mRetrofit = getRetrofitInstance();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YelpWebservice webservice = mRetrofit.create(YelpWebservice.class);
        Map<String, String> params = new HashMap<>();
        params.put("term", "restaurants");
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("limit", "50");

        Call<YelpSearchResponse> mCurrentCall = webservice.getBusinessSearch(params);
        mCurrentCall.enqueue(callback);
    }

    public void getBusinessPhotos(String businessId, Callback<BusinessDetail> callback) {
        if (mRetrofit == null) {
            try {
                mRetrofit = getRetrofitInstance();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YelpWebservice webservice = mRetrofit.create(YelpWebservice.class);
        Call<BusinessDetail> call = webservice.getBusinessPhotos(businessId);
        call.enqueue(callback);
    }

    public void cancelOngoingCommand() {
        if (mClient != null) {
            mClient.dispatcher().cancelAll();
        }
    }
}
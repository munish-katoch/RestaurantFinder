package com.katoch.restaurantfinder.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.katoch.restaurantfinder.data.Webservice;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebModule {

    @Provides
    public Retrofit getRetrofit() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(Webservice.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                // Need for RxJava adapter.
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + Webservice.API_KEY)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
    }

    @Singleton
    @Provides
    public Webservice getWebService() {
        return getRetrofit().create(Webservice.class);
    }
}

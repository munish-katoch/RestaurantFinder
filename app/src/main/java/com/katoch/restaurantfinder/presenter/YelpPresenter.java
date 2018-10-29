package com.katoch.restaurantfinder.presenter;

import android.content.Context;

import com.katoch.restaurantfinder.data.BusinessDetail;
import com.katoch.restaurantfinder.data.YelpRepository;
import com.katoch.restaurantfinder.data.YelpSearchResponse;
import com.katoch.restaurantfinder.view.IView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YelpPresenter implements IPresenter{

    private static final String TAG = "YelpPresenter";
    private YelpRepository yelpRepository;
    private IView mActivityView;
    private Context mContext;

    YelpPresenter(Context context) {
        yelpRepository = new YelpRepository();
        mContext = context;
    }

    @Override
    public void detach() {
        yelpRepository.cancelOngoingCommand();
        yelpRepository = null;
        mActivityView = null;
        mContext = null;
    }

    @Override
    public void attach(IView view) {
        mActivityView = view;
    }

    @Override
    public void requestBusinessesInfo(String latitude, String longitude) {
        Callback<YelpSearchResponse> callback = new Callback <YelpSearchResponse>() {
            @Override
            public void onResponse(Call<YelpSearchResponse> call, Response<YelpSearchResponse> response) {
                YelpSearchResponse response1 = response.body();
                mActivityView.setBusinessesInfo(response1.getBusinessesSortByCategory());
            }
            @Override
            public void onFailure(Call<YelpSearchResponse> call, Throwable t) {
                mActivityView.onFailure(mContext.getString(com.katoch.restaurantfinder.R.string.error_failure_yelp_response) + t.getMessage());
            }
        };
        yelpRepository.getBusinessesSortByCategory(latitude,longitude,callback);
    }

    @Override
    public void requestBusinessPhotos(String businessId) {
        Callback<BusinessDetail> callback = new Callback <BusinessDetail>() {
            @Override
            public void onResponse(Call<BusinessDetail> call, Response<BusinessDetail> response) {
                mActivityView.setBusinessPhoto(response.body().getPhotos());
            }

            @Override
            public void onFailure(Call<BusinessDetail> call, Throwable t) {
                mActivityView.onFailure(mContext.getString(com.katoch.restaurantfinder.R.string.error_failure_yelp_response) + t.getMessage());
            }

        };
        yelpRepository.getBusinessPhotos(businessId,callback);
    }

}

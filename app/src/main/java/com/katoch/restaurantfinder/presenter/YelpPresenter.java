package com.katoch.restaurantfinder.presenter;

import android.content.Context;
import android.util.Log;

import com.katoch.restaurantfinder.Utils;
import com.katoch.restaurantfinder.model.Business;
import com.katoch.restaurantfinder.model.BusinessDetail;
import com.katoch.restaurantfinder.model.YelpRepository;
import com.katoch.restaurantfinder.model.YelpSearchResponse;
import com.katoch.restaurantfinder.view.IView;

import java.util.ArrayList;

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
    public void onSearch(String searchStr) {

    }

    @Override
    public void onSort() {

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
        //Check internet connection. Error if No Internet connect to UI.
        if(!Utils.isNetworkAvailable(mContext)) {

            return;
        }

        Callback<YelpSearchResponse> callback = new Callback <YelpSearchResponse>() {
            @Override
            public void onResponse(Call<YelpSearchResponse> call, Response<YelpSearchResponse> response) {
                YelpSearchResponse response1 = response.body();
//                Log.d(TAG,"Total =" + response1.getTotal());
//                ArrayList<Business> list = response1.getBusinesses();
//                Log.d(TAG,"Name =" + list.get(0).getName());
//                Log.d(TAG,"Businesses size =" +  list.size());
                //View->Update UI.
                mActivityView.setBusinessesInfo(response1.getBusinesses());
            }
            @Override
            public void onFailure(Call<YelpSearchResponse> call, Throwable t) {
                //View -> OnError. Error= t.getMessage()
                mActivityView.onFailure(mContext.getString(com.katoch.restaurantfinder.R.string.error_failure_yelp_response) + t.getMessage());
            }
        };
        yelpRepository.getBusinessesSortByCategory(latitude,longitude,callback);
    }

    @Override
    public void requestBusinessPhotos(String businessId) {
        //Check internet connection. Error if No Internet connect to UI.
        if(!Utils.isNetworkAvailable(mContext)) {
            return;
        }

        Callback<BusinessDetail> callback = new Callback <BusinessDetail>() {
            @Override
            public void onResponse(Call<BusinessDetail> call, Response<BusinessDetail> response) {
//                //View->Update UI.
                mActivityView.setBusinessPhoto(response.body().getPhotos());
            }

            @Override
            public void onFailure(Call<BusinessDetail> call, Throwable t) {
                //View -> OnError. Error= t.getMessage()
                mActivityView.onFailure(mContext.getString(com.katoch.restaurantfinder.R.string.error_failure_yelp_response) + t.getMessage());
            }

        };
        yelpRepository.getBusinessPhotos(businessId,callback);
    }

}

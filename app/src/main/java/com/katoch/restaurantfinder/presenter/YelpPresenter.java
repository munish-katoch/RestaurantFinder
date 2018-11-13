package com.katoch.restaurantfinder.presenter;

import com.katoch.restaurantfinder.data.BusinessDetail;
import com.katoch.restaurantfinder.data.YelpRepository;
import com.katoch.restaurantfinder.data.YelpSearchResponse;
import com.katoch.restaurantfinder.view.IView;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YelpPresenter implements IPresenter{

    private static final String TAG = "YelpPresenter";

    private YelpRepository mYelpRepository;
    private IView mActivityView;

    @Inject
    public YelpPresenter(YelpRepository yelpRepository) {
        mYelpRepository = yelpRepository;
    }

    @Override
    public void detach() {
        mYelpRepository.cancelOngoingCommand();
        mYelpRepository = null;
        mActivityView = null;
    }

    @Override
    public void attach(IView view) {
        mActivityView = view;
    }

    @Override
    public void requestBusinessesInfo(String latitude, String longitude) {
        mYelpRepository.getBusinessesSortByCategory(latitude,longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<YelpSearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(YelpSearchResponse yelpSearchResponse) {
                        mActivityView.setBusinessesInfo(yelpSearchResponse.getBusinessesSortByCategory());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivityView.onFailure(e.getMessage());

                    }
                });

    }

    @Override
    public void requestBusinessPhotos(String businessId) {
        mYelpRepository.getBusinessPhotos(businessId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BusinessDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(BusinessDetail businessDetail) {
                        mActivityView.setBusinessPhoto(businessDetail.getPhotos());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivityView.onFailure(e.getMessage());
                    }
                });

    }

}

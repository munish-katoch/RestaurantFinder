package com.katoch.restaurantfinder.presenter;

import com.katoch.restaurantfinder.view.IView;

public interface IPresenter {
    void attach(IView view);
    void requestBusinessesInfo(String latitude, String longitude);
    void requestBusinessPhotos(String businessId);
    void onSearch(String searchStr);
    void onSort();
    void detach();

}

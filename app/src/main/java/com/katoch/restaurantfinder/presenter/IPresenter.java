package com.katoch.restaurantfinder.presenter;

import com.katoch.restaurantfinder.view.IView;

public interface IPresenter {
    void attach(IView view);
    void requestData(String latitude, String longitude);
    void onSearch(String searchStr);
    void onSort();
    void detach();

}

package com.katoch.restaurantfinder.di;

import com.katoch.restaurantfinder.data.YelpRepository;
import com.katoch.restaurantfinder.presenter.YelpPresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module (includes = RepositoryModule.class)
public class PresenterModule {
    @Inject public YelpRepository mRepository;

    @Provides
    public YelpPresenter getPresenter() {
        return new YelpPresenter(mRepository);
    }


}

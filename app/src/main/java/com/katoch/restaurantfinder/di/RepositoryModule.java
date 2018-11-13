package com.katoch.restaurantfinder.di;

import com.katoch.restaurantfinder.data.YelpRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    public YelpRepository getRepository(){
        return new YelpRepository();
    }
}

package com.katoch.restaurantfinder.di;

import dagger.Component;

@Component(modules = {WebModule.class,PresenterModule.class,RepositoryModule.class})
public interface AppComponent {
}

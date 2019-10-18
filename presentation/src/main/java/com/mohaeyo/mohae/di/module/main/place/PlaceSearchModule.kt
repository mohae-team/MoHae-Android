package com.mohaeyo.mohae.di.module.main.place

import com.mohaeyo.mohae.di.scope.PlaceFragmentScope
import com.mohaeyo.mohae.viewmodel.main.place.PlaceSearchViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PlaceSearchModule {
    @PlaceFragmentScope
    @Provides
    fun provideViewModelFactory(): PlaceSearchViewModelFactory
            = PlaceSearchViewModelFactory()
}
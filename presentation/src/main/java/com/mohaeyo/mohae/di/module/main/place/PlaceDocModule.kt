package com.mohaeyo.mohae.di.module.main.place

import com.mohaeyo.mohae.di.scope.PlaceFragmentScope
import com.mohaeyo.mohae.viewmodel.main.place.PlaceDocViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PlaceDocModule {
    @PlaceFragmentScope
    @Provides
    fun provideViewModelFactory(): PlaceDocViewModelFactory
            = PlaceDocViewModelFactory()
}
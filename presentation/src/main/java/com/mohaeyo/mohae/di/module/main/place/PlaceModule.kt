package com.mohaeyo.mohae.di.module.main.place

import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.viewmodel.facotry.PlaceViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PlaceModule {
    @MainFragmentScope
    @Provides
    fun provideViewModelFactory(): PlaceViewModelFactory
            = PlaceViewModelFactory()
}
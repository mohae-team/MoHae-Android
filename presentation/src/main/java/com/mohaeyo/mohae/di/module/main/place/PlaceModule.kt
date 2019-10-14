package com.mohaeyo.mohae.di.module.main.place

import com.mohaeyo.mohae.di.scope.PlaceFragmentScope
import com.mohaeyo.mohae.ui.fragment.main.place.PlaceDocFragment
import com.mohaeyo.mohae.ui.fragment.main.place.PlaceSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PlaceModule {
    @PlaceFragmentScope
    @ContributesAndroidInjector(modules = [PlaceSearchModule::class])
    abstract fun providePlaceSearchFragment(): PlaceSearchFragment

    @PlaceFragmentScope
    @ContributesAndroidInjector(modules = [PlaceDocModule::class])
    abstract fun providePlaceDocFragment(): PlaceDocFragment

}
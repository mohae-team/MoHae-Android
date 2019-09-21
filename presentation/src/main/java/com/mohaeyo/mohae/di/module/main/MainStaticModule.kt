package com.mohaeyo.mohae.di.module.main

import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.viewmodel.facotry.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainStaticModule {
    @FragmentScope
    @Provides
    fun provideViewModelFactory(): MainViewModelFactory
            = MainViewModelFactory()
}
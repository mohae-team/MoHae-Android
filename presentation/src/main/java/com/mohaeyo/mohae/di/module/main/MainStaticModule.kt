package com.mohaeyo.mohae.di.module.main

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.ui.fragment.main.MainFragment
import com.mohaeyo.mohae.viewmodel.main.MainViewModel
import com.mohaeyo.mohae.viewmodel.main.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainStaticModule {
    @FragmentScope
    @Provides
    fun provideViewModelFactory(): MainViewModelFactory
            = MainViewModelFactory()

    @FragmentScope
    @Provides
    fun provideViewModel(fragment: MainFragment): MainViewModel
            = ViewModelProviders.of(fragment).get(MainViewModel::class.java)
}
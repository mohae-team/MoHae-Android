package com.mohaeyo.mohae.di.module

import com.mohaeyo.mohae.di.module.main.MainModule
import com.mohaeyo.mohae.di.scope.ActivityScope
import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}
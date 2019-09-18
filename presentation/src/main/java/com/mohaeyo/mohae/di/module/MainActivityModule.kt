package com.mohaeyo.mohae.di.module

import com.mohaeyo.mohae.di.module.main.MainModule
import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.ui.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainFragment(): MainFragment
}
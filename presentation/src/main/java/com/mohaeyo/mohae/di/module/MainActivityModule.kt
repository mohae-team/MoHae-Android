package com.mohaeyo.mohae.di.module

import com.mohaeyo.mohae.di.module.login.LoginModule
import com.mohaeyo.mohae.di.module.main.MainModule
import com.mohaeyo.mohae.di.module.main.MainStaticModule
import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.ui.fragment.LoginFragment
import com.mohaeyo.mohae.ui.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainStaticModule::class])
    abstract fun mainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun loginFragment(): LoginFragment
}
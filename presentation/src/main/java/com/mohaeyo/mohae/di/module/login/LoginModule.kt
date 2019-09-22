package com.mohaeyo.mohae.di.module.login

import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.viewmodel.login.LoginViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @FragmentScope
    @Provides
    fun provideViewModelFactory(): LoginViewModelFactory
            = LoginViewModelFactory()
}
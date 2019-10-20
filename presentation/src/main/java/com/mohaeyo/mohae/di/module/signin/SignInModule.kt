package com.mohaeyo.mohae.di.module.signin

import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.viewmodel.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignInModule {
    @FragmentScope
    @Provides
    fun provideViewModelFactory(): SignInViewModelFactory
            = SignInViewModelFactory()
}
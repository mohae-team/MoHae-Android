package com.mohaeyo.mohae.di.module.signup

import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignUpModule {
    @FragmentScope
    @Provides
    fun provideViewModelFactory(): SignUpViewModelFactory
            = SignUpViewModelFactory()
}
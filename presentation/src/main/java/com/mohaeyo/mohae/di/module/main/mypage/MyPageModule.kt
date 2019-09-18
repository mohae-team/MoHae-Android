package com.mohaeyo.mohae.di.module.main.mypage

import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.viewmodel.facotry.MyPageViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MyPageModule {
    @MainFragmentScope
    @Provides
    fun provideViewModelFactory(): MyPageViewModelFactory
            = MyPageViewModelFactory()
}
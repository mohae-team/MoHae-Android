package com.mohaeyo.mohae.di.module.main.mypage

import com.mohaeyo.mohae.di.scope.MyPageFragmentScope
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MyPageProfileEditModule {

    @MyPageFragmentScope
    @Provides
    fun provideViewModelFactory(): MyPageProfileEditViewModelFactory
            = MyPageProfileEditViewModelFactory()

}
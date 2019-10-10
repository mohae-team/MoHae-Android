package com.mohaeyo.mohae.di.module.main.mypage

import com.mohaeyo.mohae.di.scope.MyPageFragmentScope
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MyPageProfileModule {

    @MyPageFragmentScope
    @Provides
    fun provideViewModelFactory(): MyPageProfileViewModelFactory
            = MyPageProfileViewModelFactory()

}
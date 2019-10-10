package com.mohaeyo.mohae.di.module.main.mypage

import com.mohaeyo.mohae.di.scope.MyPageFragmentScope
import com.mohaeyo.mohae.ui.fragment.main.mypage.MyPageProfileEditFragment
import com.mohaeyo.mohae.ui.fragment.main.mypage.MyPageProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MyPageModule {

    @MyPageFragmentScope
    @ContributesAndroidInjector(modules = [MyPageProfileModule::class])
    abstract fun myPageProfileFragment(): MyPageProfileFragment

    @MyPageFragmentScope
    @ContributesAndroidInjector(modules = [MyPageProfileEditModule::class])
    abstract fun myPageProfileEditFragment(): MyPageProfileEditFragment
}
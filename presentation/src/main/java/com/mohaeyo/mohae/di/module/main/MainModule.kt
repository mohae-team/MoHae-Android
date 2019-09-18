package com.mohaeyo.mohae.di.module.main

import com.mohaeyo.mohae.di.module.main.feedback.FeedbackModule
import com.mohaeyo.mohae.di.module.main.group.GroupModule
import com.mohaeyo.mohae.di.module.main.mypage.MyPageModule
import com.mohaeyo.mohae.di.module.main.place.PlaceModule
import com.mohaeyo.mohae.di.module.main.qa.QAModule
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.ui.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @MainFragmentScope
    @ContributesAndroidInjector(modules = [FeedbackModule::class])
    abstract fun feedbackFragment(): FeedbackFragment

    @MainFragmentScope
    @ContributesAndroidInjector(modules = [QAModule::class])
    abstract fun qaFragment(): QAFragment

    @MainFragmentScope
    @ContributesAndroidInjector(modules = [GroupModule::class])
    abstract fun groupFragment(): GroupFragment

    @MainFragmentScope
    @ContributesAndroidInjector(modules = [PlaceModule::class])
    abstract fun placeFragment(): PlaceFragment

    @MainFragmentScope
    @ContributesAndroidInjector(modules = [MyPageModule::class])
    abstract fun mypageFragment(): MyPageFragment
}
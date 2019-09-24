package com.mohaeyo.mohae.di.module.main.group

import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.viewmodel.main.group.GroupViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GroupStaticModule {
    @MainFragmentScope
    @Provides
    fun provideViewModelFactory(): GroupViewModelFactory
            = GroupViewModelFactory()
}
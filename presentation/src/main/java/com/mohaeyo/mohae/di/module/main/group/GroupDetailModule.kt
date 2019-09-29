package com.mohaeyo.mohae.di.module.main.group

import com.mohaeyo.mohae.di.scope.GroupFragmentScope
import com.mohaeyo.mohae.viewmodel.main.group.detail.GroupDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GroupDetailModule {
    @GroupFragmentScope
    @Provides
    fun provideViewModelFactory(): GroupDetailViewModelFactory
            = GroupDetailViewModelFactory()
}
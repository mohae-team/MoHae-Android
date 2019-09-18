package com.mohaeyo.mohae.di.module.main.group

import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.viewmodel.facotry.GroupViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GroupModule {
    @MainFragmentScope
    @Provides
    fun provideViewModelFactory(): GroupViewModelFactory
            = GroupViewModelFactory()
}
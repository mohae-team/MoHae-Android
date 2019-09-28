package com.mohaeyo.mohae.di.module.main.group

import com.mohaeyo.mohae.di.scope.GroupFragmentScope
import com.mohaeyo.mohae.viewmodel.main.group.doc.GroupDocViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GroupDocModule {
    @GroupFragmentScope
    @Provides
    fun provideViewModelFactory(): GroupDocViewModelFactory
            = GroupDocViewModelFactory()
}
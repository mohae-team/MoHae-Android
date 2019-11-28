package com.mohaeyo.mohae.di.module.main.group

import com.mohaeyo.domain.usecase.GetGroupListUseCase
import com.mohaeyo.mohae.di.scope.GroupFragmentScope
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.viewmodel.main.group.list.GroupListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GroupListModule {
    @GroupFragmentScope
    @Provides
    fun provideViewModelFactory(
        getGroupListUseCase: GetGroupListUseCase,
        groupMapper: GroupMapper
    ): GroupListViewModelFactory
            = GroupListViewModelFactory(
        getGroupListUseCase,
        groupMapper
    )
}
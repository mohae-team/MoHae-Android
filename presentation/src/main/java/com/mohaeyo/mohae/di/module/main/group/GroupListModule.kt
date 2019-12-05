package com.mohaeyo.mohae.di.module.main.group

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.GetGroupListUseCase
import com.mohaeyo.mohae.di.scope.GroupFragmentScope
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.ui.fragment.main.group.GroupListFragment
import com.mohaeyo.mohae.viewmodel.main.group.list.GroupListViewModel
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

    @GroupFragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: GroupListViewModelFactory,
        fragment: GroupListFragment
    ): GroupListViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(GroupListViewModel::class.java)
}
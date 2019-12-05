package com.mohaeyo.mohae.di.module.main.group

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.CreateGroupUseCase
import com.mohaeyo.mohae.di.scope.GroupFragmentScope
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.ui.fragment.main.group.GroupDocFragment
import com.mohaeyo.mohae.viewmodel.main.group.doc.GroupDocViewModel
import com.mohaeyo.mohae.viewmodel.main.group.doc.GroupDocViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GroupDocModule {
    @GroupFragmentScope
    @Provides
    fun provideViewModelFactory(
        createGroupUseCase: CreateGroupUseCase,
        groupMapper: GroupMapper
    ): GroupDocViewModelFactory
            = GroupDocViewModelFactory(
        createGroupUseCase,
        groupMapper
    )

    @GroupFragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: GroupDocViewModelFactory,
        fragment: GroupDocFragment
    ): GroupDocViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(GroupDocViewModel::class.java)
}
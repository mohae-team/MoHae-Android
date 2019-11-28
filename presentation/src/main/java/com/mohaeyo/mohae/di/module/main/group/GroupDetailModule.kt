package com.mohaeyo.mohae.di.module.main.group

import com.mohaeyo.domain.usecase.CancelJoinGroupUseCase
import com.mohaeyo.domain.usecase.GetGroupDetailUseCase
import com.mohaeyo.domain.usecase.JoinGroupUseCase
import com.mohaeyo.mohae.di.scope.GroupFragmentScope
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.viewmodel.main.group.detail.GroupDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GroupDetailModule {
    @GroupFragmentScope
    @Provides
    fun provideViewModelFactory(
        getGroupDetailUseCase: GetGroupDetailUseCase,
        joinGroupUseCase: JoinGroupUseCase,
        cancelJoinGroupUseCase: CancelJoinGroupUseCase,
        groupMapper: GroupMapper
    ): GroupDetailViewModelFactory
            = GroupDetailViewModelFactory(
        getGroupDetailUseCase,
        joinGroupUseCase,
        cancelJoinGroupUseCase,
        groupMapper
    )
}
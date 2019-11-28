package com.mohaeyo.mohae.di.module.main.group

import com.mohaeyo.domain.usecase.CreateGroupUseCase
import com.mohaeyo.mohae.di.scope.GroupFragmentScope
import com.mohaeyo.mohae.mapper.GroupMapper
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
}
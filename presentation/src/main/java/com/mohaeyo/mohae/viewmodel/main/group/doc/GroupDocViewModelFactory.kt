package com.mohaeyo.mohae.viewmodel.main.group.doc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.CreateGroupUseCase
import com.mohaeyo.mohae.mapper.GroupMapper

class GroupDocViewModelFactory(
    private val createGroupUseCase: CreateGroupUseCase,
    private val groupMapper: GroupMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        CreateGroupUseCase::class.java,
        GroupMapper::class.java
    ).newInstance(
        createGroupUseCase,
        groupMapper
    )
}
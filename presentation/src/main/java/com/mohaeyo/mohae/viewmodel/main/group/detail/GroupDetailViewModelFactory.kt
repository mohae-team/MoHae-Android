package com.mohaeyo.mohae.viewmodel.main.group.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.CancelJoinGroupUseCase
import com.mohaeyo.domain.usecase.GetGroupDetailUseCase
import com.mohaeyo.domain.usecase.JoinGroupUseCase
import com.mohaeyo.mohae.mapper.GroupMapper

class GroupDetailViewModelFactory(
    private val getGroupDetailUseCase: GetGroupDetailUseCase,
    private val joinGroupUseCase: JoinGroupUseCase,
    private val cancelJoinGroupUseCase: CancelJoinGroupUseCase,
    private val groupMapper: GroupMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetGroupDetailUseCase::class.java,
        JoinGroupUseCase::class.java,
        CancelJoinGroupUseCase::class.java,
        GroupMapper::class.java
    ).newInstance(
        getGroupDetailUseCase, joinGroupUseCase, cancelJoinGroupUseCase, groupMapper)
}
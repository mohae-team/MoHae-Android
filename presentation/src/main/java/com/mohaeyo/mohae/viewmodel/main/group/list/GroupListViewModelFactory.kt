package com.mohaeyo.mohae.viewmodel.main.group.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetGroupListUseCase
import com.mohaeyo.mohae.mapper.GroupMapper

class GroupListViewModelFactory(
    private val getGroupListUseCase: GetGroupListUseCase,
    private val groupMapper: GroupMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetGroupListUseCase::class.java,
        GroupMapper::class.java
    ).newInstance(
        getGroupListUseCase,
        groupMapper
    )
}
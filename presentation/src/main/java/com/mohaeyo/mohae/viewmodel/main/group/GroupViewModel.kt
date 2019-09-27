package com.mohaeyo.mohae.viewmodel.main.group

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.GroupModel

class GroupViewModel(): LifecycleCallback, BaseViewModel() {

    val groupList = MutableLiveData<ArrayList<GroupModel>>().apply {
        val array = ArrayList<GroupModel>()
        array.add(GroupModel(
            "리스트를 불러올 수 없습니다.",
            "네트워크 상태를 확인해주세요.",
            "네트워크 상태를 확인해주세요.",
            "네트워크 상태를 확인해주세요.",
            "네트워크 상태를 확인해주세요.",
            "네트워크 상태를 확인해주세요."
        ))
        value = array
    }

    val selectedGroupItem = MutableLiveData<GroupModel>().apply {
        value = GroupModel(
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다."
        )
    }

    val startListToDetailEvent = SingleLiveEvent<GroupModel>()
    val startListToDocEvent = SingleLiveEvent<Unit>()
    val startDocToListEvent = SingleLiveEvent<Unit>()
    val startDetailToListEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickListToDoc() {
        startListToDocEvent.call()
    }

    fun clickPostGroup() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }

    fun clickDetailToList() {
        startDetailToListEvent.call()
    }
    fun clickGroupItem(group: GroupModel) {
        startListToDetailEvent.value = group
    }

    fun clickJoinGroup() {
        startDetailToListEvent.call()
    }

}
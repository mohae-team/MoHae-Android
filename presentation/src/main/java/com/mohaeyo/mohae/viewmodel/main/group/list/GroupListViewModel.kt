package com.mohaeyo.mohae.viewmodel.main.group.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.GroupModel

class GroupListViewModel: BaseViewModel(), LifecycleCallback {

    val groupList = MutableLiveData<ArrayList<GroupModel>>().apply {
        val array = ArrayList<GroupModel>()
        array.add(
            GroupModel(
                "리스트를 불러올 수 없습니다.",
                "네트워크 상태를 확인해주세요.",
                "네트워크 상태를 확인해주세요.",
                "네트워크 상태를 확인해주세요.",
                "네트워크 상태를 확인해주세요.",
                "네트워크 상태를 확인해주세요."
            )
        )
        value = array
    }

    val startListToDetailEvent = SingleLiveEvent<GroupModel>()
    val startListToDocEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickListToDoc() {
        startListToDocEvent.call()
    }

    fun clickGroupItem(group: GroupModel) {
        startListToDetailEvent.value = group
    }
}
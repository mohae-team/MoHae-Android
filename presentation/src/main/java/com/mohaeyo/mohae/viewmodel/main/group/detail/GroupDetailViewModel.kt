package com.mohaeyo.mohae.viewmodel.main.group.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.GroupModel

class GroupDetailViewModel: LifecycleCallback, BaseViewModel() {

    val selectedGroupId = MutableLiveData<Int>()

    val selectedGroupItem = MutableLiveData<GroupModel>().apply {
        value = GroupModel(
            0,
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다."
        )
    }
    val startDetailToListEvent = SingleLiveEvent<Unit>()
    val startDetailToDialogEvent = SingleLiveEvent<Unit>()
    val closeDialog = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickDetailToList() {
        startDetailToListEvent.call()
    }

    fun clickJoinGroup() {
        startDetailToDialogEvent.call()
    }

    fun clickJoin() {
        closeDialog.call()
    }

    fun clickLeave() {
        closeDialog.call()
    }
}
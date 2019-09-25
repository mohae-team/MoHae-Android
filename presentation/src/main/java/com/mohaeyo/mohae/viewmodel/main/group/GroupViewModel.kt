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
            "dfsdfdsfds",
            "dfsdfdsfds",
            "fdsfdsfds",
            "13/50"
        ))
        array.add(GroupModel(
            "dfsdfdsfds",
            "dfsdfdsfds",
            "fdsfdsfds",
            "13/50"
        ))
        array.add(GroupModel(
            "dfsdfdsfds",
            "dfsdfdsfds",
            "fdsfdsfds",
            "13/50"
        ))
        value = array
    }

    val startGroupDetailEvent = SingleLiveEvent<GroupModel>()
    val startGroupDocEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickFloatingBtn() {
        startGroupDocEvent.call()
    }

    fun groupItemClick(group: GroupModel) {
        startGroupDetailEvent.value = group
    }
}
package com.mohaeyo.mohae.viewmodel.main.group.doc

import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent

class GroupDocViewModel: BaseViewModel() {

    val startDocToListEvent = SingleLiveEvent<Unit>()

    fun clickPostGroup() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
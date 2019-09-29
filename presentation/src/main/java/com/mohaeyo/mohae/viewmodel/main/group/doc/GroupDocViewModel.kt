package com.mohaeyo.mohae.viewmodel.main.group.doc

import androidx.lifecycle.Lifecycle
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent

class GroupDocViewModel: LifecycleCallback, BaseViewModel() {

    val startDocToListEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickPostGroup() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
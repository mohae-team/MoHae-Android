package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.Lifecycle
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent

class PlaceDocViewModel(): BaseViewModel() {

    val startDocToListEvent = SingleLiveEvent<Unit>()

    fun clickPostPlace() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
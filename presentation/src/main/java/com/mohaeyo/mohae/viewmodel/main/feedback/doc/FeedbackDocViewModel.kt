package com.mohaeyo.mohae.viewmodel.main.feedback.doc

import androidx.lifecycle.Lifecycle
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent

class FeedbackDocViewModel(): LifecycleCallback, BaseViewModel() {
    val startDocToListEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickPostFeedback() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
package com.mohaeyo.mohae.viewmodel.main.feedback.doc

import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent

class FeedbackDocViewModel(): BaseViewModel() {
    val startDocToListEvent = SingleLiveEvent<Unit>()

    fun clickPostFeedback() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
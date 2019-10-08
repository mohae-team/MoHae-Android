package com.mohaeyo.mohae.viewmodel.main.qa.questionDoc

import androidx.lifecycle.Lifecycle
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent

class QAQuestionDocViewModel(): LifecycleCallback, BaseViewModel() {
    val startDocToListEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickPostQuestion() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
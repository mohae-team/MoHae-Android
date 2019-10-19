package com.mohaeyo.mohae.viewmodel.main.qa.questionDoc

import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent

class QAQuestionDocViewModel(): BaseViewModel() {
    val startDocToListEvent = SingleLiveEvent<Unit>()

    fun clickPostQuestion() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
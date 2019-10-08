package com.mohaeyo.mohae.viewmodel.main.qa.questionDetail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.QuestionModel

class QAAnswerDocViewModel(): LifecycleCallback, BaseViewModel() {

    val selectedQuestionId = MutableLiveData<Int>()

    val startDocToListEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickPostAnswer() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
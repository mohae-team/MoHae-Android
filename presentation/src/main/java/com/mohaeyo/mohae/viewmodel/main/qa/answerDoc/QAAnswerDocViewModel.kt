package com.mohaeyo.mohae.viewmodel.main.qa.answerDoc

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent

class QAAnswerDocViewModel(): BaseViewModel() {

    val selectedQuestionId = MutableLiveData<Int>()

    val startDocToListEvent = SingleLiveEvent<Unit>()

    fun clickPostAnswer() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
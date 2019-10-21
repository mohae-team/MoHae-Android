package com.mohaeyo.mohae.viewmodel.main.qa.questionDoc

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent

class QAQuestionDocViewModel(): BaseViewModel() {

    val titleText = MutableLiveData<String>()
    val summaryText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()

    val startDocToListEvent = SingleLiveEvent<Unit>()
    val titleErrorEvent = SingleLiveEvent<String>()
    val summaryErrorEvent = SingleLiveEvent<String>()
    val descriptionErrorEvent = SingleLiveEvent<String>()

    fun clickPostQuestion() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
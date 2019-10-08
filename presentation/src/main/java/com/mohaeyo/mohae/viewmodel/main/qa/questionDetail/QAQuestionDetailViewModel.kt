package com.mohaeyo.mohae.viewmodel.main.qa.questionDetail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.QuestionModel

class QAQuestionDetailViewModel(): LifecycleCallback, BaseViewModel() {

    val selectedQuestionId = MutableLiveData<Int>()

    val selectedQuestionItem = MutableLiveData<QuestionModel>().apply {
        value = QuestionModel(
            0,
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            ArrayList()
        )
    }
    val startDetailToQuestionListEvent = SingleLiveEvent<Unit>()
    val startDetailToAnswerListEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickDetailToList() {
        startDetailToQuestionListEvent.call()
    }

    fun clickAnswers() {
        startDetailToAnswerListEvent.call()
    }
}
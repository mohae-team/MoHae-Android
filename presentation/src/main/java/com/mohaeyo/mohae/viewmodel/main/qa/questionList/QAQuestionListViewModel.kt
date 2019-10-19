package com.mohaeyo.mohae.viewmodel.main.qa.questionList

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.QuestionModel

class QAQuestionListViewModel(): BaseViewModel() {
    val questionList = MutableLiveData<ArrayList<QuestionModel>>().apply {
        val array = ArrayList<QuestionModel>()
        array.add(
            QuestionModel(
                0,
                "리스트를 불러올 수 없습니다.",
                "네트워크 상태를 확인해주세요.",
                "네트워크 상태를 확인해주세요.",
                "네트워크 상태를 확인해주세요.",
                "네트워크 상태를 확인해주세요.",
                ArrayList()
            )
        )
        value = array
    }

    val startListToDetailEvent = SingleLiveEvent<QuestionModel>()
    val startListToDocEvent = SingleLiveEvent<Unit>()

    fun clickListToDoc() {
        startListToDocEvent.call()
    }

    fun clickQuestionItem(question: QuestionModel) {
        startListToDetailEvent.value = question
    }
}
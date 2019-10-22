package com.mohaeyo.mohae.viewmodel.main.qa.answerList

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.QuestionModel

class QAAnswerListViewModel(): BaseViewModel() {

    val selectedQuestionId = MutableLiveData<Int>()

    val selectedQuestionItem = MutableLiveData<QuestionModel>().apply {
        value = QuestionModel(
            0,
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            byteArrayOf(),
            "데이터를 불러올 수 없습니다.",
            ArrayList()
        )
    }

    val answerList = MutableLiveData<ArrayList<QuestionModel.AnswerModel>>().apply {
        val array = ArrayList<QuestionModel.AnswerModel>()
        array.add(
            QuestionModel.AnswerModel(
                "네트워크 연결을 확인해주세요.",
                "네트워크 연결을 확인해주세요."
            )
        )
        value = array
    }

    val startListToDetailEvent = SingleLiveEvent<Unit>()
    val startListToDocEvent = SingleLiveEvent<Unit>()

    fun clickListToDetail() {
        startListToDetailEvent.call()
    }

    fun clickWriteAnswer() {
        startListToDocEvent.call()
    }
}
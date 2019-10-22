package com.mohaeyo.mohae.viewmodel.main.feedback.list

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.FeedbackModel

class FeedbackListViewModel(): BaseViewModel() {
    val feedbackList = MutableLiveData<ArrayList<FeedbackModel>>().apply {
        val array = ArrayList<FeedbackModel>()
        array.add(
            FeedbackModel(
                0,
                "리스트를 불러올 수 없습니다.",
                "네트워크 상태를 확인해주세요.",
                "네트워크 상태를 확인해주세요.",
                byteArrayOf(),
                "네트워크 상태를 확인해주세요.",
                0.toString(),
                0.toString(),
                false
            )
        )
        value = array
    }

    val startListToDetailEvent = SingleLiveEvent<FeedbackModel>()
    val startListToDocEvent = SingleLiveEvent<Unit>()

    fun clickListToDoc() {
        startListToDocEvent.call()
    }

    fun clickFeedbackItem(feedback: FeedbackModel) {
        startListToDetailEvent.value = feedback
    }
}
package com.mohaeyo.mohae.viewmodel.main.feedback.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.FeedbackModel

class FeedbackDetailViewModel(): BaseViewModel() {

    val selectedFeedbackId = MutableLiveData<Int>()

    val selectedFeedbackItem = MutableLiveData<FeedbackModel>().apply {
        value = FeedbackModel(
            0,
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            "데이터를 불러올 수 없습니다.",
            0.toString(),
            0.toString())
    }
    val startDetailToListEvent = SingleLiveEvent<Unit>()
    val startDetailToDialogEvent = SingleLiveEvent<Unit>()
    val closeDialog = SingleLiveEvent<Unit>()

    fun clickDetailToList() {
        startDetailToListEvent.call()
    }

    fun clickLikeOrHate() {
        startDetailToDialogEvent.call()
    }

    fun clickLike() {
        closeDialog.call()
    }

    fun clickHate() {
        closeDialog.call()
    }
}
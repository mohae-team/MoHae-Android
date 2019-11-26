package com.mohaeyo.mohae.viewmodel.main.feedback.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetFeedbackListUseCase
import com.mohaeyo.mohae.mapper.FeedbackMapper

class FeedbackListViewModelFactory(
    private val getFeedbackListUseCase: GetFeedbackListUseCase,
    private val feedbackMapper: FeedbackMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetFeedbackListUseCase::class.java,
        FeedbackMapper::class.java
    ).newInstance(
        getFeedbackListUseCase,
        feedbackMapper
    )
}
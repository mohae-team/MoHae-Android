package com.mohaeyo.mohae.viewmodel.main.feedback.doc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.CreateFeedbackUseCase
import com.mohaeyo.domain.usecase.GetFeedbackListUseCase
import com.mohaeyo.mohae.mapper.FeedbackMapper

class FeedbackDocViewModelFactory(
    private val createFeedbackUseCase: CreateFeedbackUseCase,
    private val feedbackMapper: FeedbackMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        CreateFeedbackUseCase::class.java,
        FeedbackMapper::class.java
    ).newInstance(
        createFeedbackUseCase,
        feedbackMapper
    )
}
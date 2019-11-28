package com.mohaeyo.mohae.viewmodel.main.feedback.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetFeedbackDetailUseCase
import com.mohaeyo.domain.usecase.HateFeedbackUseCase
import com.mohaeyo.domain.usecase.LikeFeedbackUseCase
import com.mohaeyo.mohae.mapper.FeedbackMapper

class FeedbackDetailViewModelFactory(
    private val getFeedbackDetailUseCase: GetFeedbackDetailUseCase,
    private val likeFeedbackUseCase: LikeFeedbackUseCase,
    private val hateFeedbackUseCase: HateFeedbackUseCase,
    private val feedbackMapper: FeedbackMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetFeedbackDetailUseCase::class.java,
        LikeFeedbackUseCase::class.java,
        HateFeedbackUseCase::class.java,
        FeedbackMapper::class.java
    ).newInstance(getFeedbackDetailUseCase, likeFeedbackUseCase, hateFeedbackUseCase, feedbackMapper)
}
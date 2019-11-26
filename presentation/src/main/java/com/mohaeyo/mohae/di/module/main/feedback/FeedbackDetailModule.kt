package com.mohaeyo.mohae.di.module.main.feedback

import com.mohaeyo.domain.usecase.GetFeedbackDetailUseCase
import com.mohaeyo.domain.usecase.HateFeedbackUseCase
import com.mohaeyo.domain.usecase.LikeFeedbackUseCase
import com.mohaeyo.mohae.di.scope.FeedbackFragmentScope
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.viewmodel.main.feedback.detail.FeedbackDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedbackDetailModule {
    @FeedbackFragmentScope
    @Provides
    fun provideViewModelFactory(
        getFeedbackDetailUseCase: GetFeedbackDetailUseCase,
        likeFeedbackUseCase: LikeFeedbackUseCase,
        hateFeedbackUseCase: HateFeedbackUseCase,
        feedbackMapper: FeedbackMapper
    ): FeedbackDetailViewModelFactory
            = FeedbackDetailViewModelFactory(getFeedbackDetailUseCase, likeFeedbackUseCase, hateFeedbackUseCase, feedbackMapper)
}
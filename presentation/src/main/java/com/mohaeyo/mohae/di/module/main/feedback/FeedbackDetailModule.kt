package com.mohaeyo.mohae.di.module.main.feedback

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.GetFeedbackDetailUseCase
import com.mohaeyo.domain.usecase.HateFeedbackUseCase
import com.mohaeyo.domain.usecase.LikeFeedbackUseCase
import com.mohaeyo.mohae.di.scope.FeedbackFragmentScope
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.ui.fragment.main.feedback.FeedbackDetailFragment
import com.mohaeyo.mohae.viewmodel.main.feedback.detail.FeedbackDetailViewModel
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

    @FeedbackFragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: FeedbackDetailViewModelFactory,
        fragment: FeedbackDetailFragment
    ): FeedbackDetailViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(FeedbackDetailViewModel::class.java)
}
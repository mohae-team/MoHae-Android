package com.mohaeyo.mohae.di.module.main.feedback

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.CreateFeedbackUseCase
import com.mohaeyo.mohae.di.scope.FeedbackFragmentScope
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.ui.fragment.main.feedback.FeedbackDocFragment
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModel
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedbackDocModule {
    @FeedbackFragmentScope
    @Provides
    fun provideViewModelFactory(
        createFeedbackUseCase: CreateFeedbackUseCase,
        feedbackMapper: FeedbackMapper
    ): FeedbackDocViewModelFactory
            = FeedbackDocViewModelFactory(createFeedbackUseCase, feedbackMapper)

    @FeedbackFragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: FeedbackDocViewModelFactory,
        fragment: FeedbackDocFragment
    ): FeedbackDocViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(FeedbackDocViewModel::class.java)
}
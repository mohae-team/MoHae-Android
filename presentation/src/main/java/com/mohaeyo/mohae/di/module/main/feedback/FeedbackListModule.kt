package com.mohaeyo.mohae.di.module.main.feedback

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.GetFeedbackListUseCase
import com.mohaeyo.mohae.di.scope.FeedbackFragmentScope
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.ui.fragment.main.feedback.FeedbackListFragment
import com.mohaeyo.mohae.viewmodel.main.feedback.list.FeedbackListViewModel
import com.mohaeyo.mohae.viewmodel.main.feedback.list.FeedbackListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedbackListModule {
    @FeedbackFragmentScope
    @Provides
    fun provideViewModelFactory(
        getFeedbackListUseCase: GetFeedbackListUseCase,
        feedbackMapper: FeedbackMapper
    ): FeedbackListViewModelFactory
            = FeedbackListViewModelFactory(getFeedbackListUseCase, feedbackMapper)

    @FeedbackFragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: FeedbackListViewModelFactory,
        fragment: FeedbackListFragment
    ): FeedbackListViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(FeedbackListViewModel::class.java)
}
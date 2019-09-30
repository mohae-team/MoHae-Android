package com.mohaeyo.mohae.di.module.main.feedback

import com.mohaeyo.mohae.di.scope.FeedbackFragmentScope
import com.mohaeyo.mohae.viewmodel.main.feedback.list.FeedbackListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedbackListModule {
    @FeedbackFragmentScope
    @Provides
    fun provideViewModelFactory(): FeedbackListViewModelFactory
            = FeedbackListViewModelFactory()
}
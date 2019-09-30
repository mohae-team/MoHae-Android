package com.mohaeyo.mohae.di.module.main.feedback

import com.mohaeyo.mohae.di.scope.FeedbackFragmentScope
import com.mohaeyo.mohae.viewmodel.main.feedback.detail.FeedbackDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedbackDetailModule {
    @FeedbackFragmentScope
    @Provides
    fun provideViewModelFactory(): FeedbackDetailViewModelFactory
            = FeedbackDetailViewModelFactory()
}
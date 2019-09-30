package com.mohaeyo.mohae.di.module.main.feedback

import com.mohaeyo.mohae.di.scope.FeedbackFragmentScope
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedbackDocModule {
    @FeedbackFragmentScope
    @Provides
    fun provideViewModelFactory(): FeedbackDocViewModelFactory
            = FeedbackDocViewModelFactory()
}
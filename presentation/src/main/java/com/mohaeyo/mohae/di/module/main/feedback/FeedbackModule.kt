package com.mohaeyo.mohae.di.module.main.feedback

import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.viewmodel.facotry.FeedbackViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedbackModule {
    @MainFragmentScope
    @Provides
    fun provideViewModelFactory(): FeedbackViewModelFactory
            = FeedbackViewModelFactory()
}
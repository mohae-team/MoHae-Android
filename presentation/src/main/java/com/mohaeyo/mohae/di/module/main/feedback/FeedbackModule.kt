package com.mohaeyo.mohae.di.module.main.feedback

import com.mohaeyo.mohae.di.scope.FeedbackFragmentScope
import com.mohaeyo.mohae.ui.dialog.FeedbackDetailDialogFragment
import com.mohaeyo.mohae.ui.fragment.main.feedback.FeedbackDetailFragment
import com.mohaeyo.mohae.ui.fragment.main.feedback.FeedbackDocFragment
import com.mohaeyo.mohae.ui.fragment.main.feedback.FeedbackListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedbackModule {

    @FeedbackFragmentScope
    @ContributesAndroidInjector(modules = [FeedbackListModule::class])
    abstract fun feedbackListFragment(): FeedbackListFragment

    @FeedbackFragmentScope
    @ContributesAndroidInjector(modules = [FeedbackDocModule::class])
    abstract fun feedbackDocFragment(): FeedbackDocFragment

    @FeedbackFragmentScope
    @ContributesAndroidInjector(modules = [FeedbackDetailModule::class])
    abstract fun feedbackDetailFragment(): FeedbackDetailFragment

    @FeedbackFragmentScope
    @ContributesAndroidInjector(modules = [FeedbackDetailModule::class])
    abstract fun feedbackDetailDialogFragment(): FeedbackDetailDialogFragment
}
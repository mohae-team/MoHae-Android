package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.ui.fragment.main.qa.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class QAModule {
    @QAFragmentScope
    @ContributesAndroidInjector(modules = [QAQuestionListModule::class])
    abstract fun qaQuestionListFragment(): QAQuestionListFragment

    @QAFragmentScope
    @ContributesAndroidInjector(modules = [QAQuestionDocModule::class])
    abstract fun qaQuestionDocFragment(): QAQuestionDocFragment

    @QAFragmentScope
    @ContributesAndroidInjector(modules = [QAQuestionDetailModule::class])
    abstract fun qaQuestionDetailFragment(): QAQuestionDetailFragment

    @QAFragmentScope
    @ContributesAndroidInjector(modules = [QAAnswerListModule::class])
    abstract fun qaAnswerListFragment(): QAAnswerListFragment

    @QAFragmentScope
    @ContributesAndroidInjector(modules = [QAAnswerDocModule::class])
    abstract fun qaAnswerDocFragment(): QAAnswerDocFragment
}
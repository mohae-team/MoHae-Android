package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAAnswerListViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class QAAnswerListModule {
    @QAFragmentScope
    @Provides
    fun provideViewModelProvide(): QAAnswerListViewModelFactory
            = QAAnswerListViewModelFactory()
}
package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.viewmodel.main.qa.questionList.QAQuestionListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class QAQuestionListModule {
    @QAFragmentScope
    @Provides
    fun provideViewModelFactory(): QAQuestionListViewModelFactory
            = QAQuestionListViewModelFactory()
}
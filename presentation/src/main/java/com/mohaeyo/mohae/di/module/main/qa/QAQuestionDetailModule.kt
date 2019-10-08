package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAQuestionDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class QAQuestionDetailModule {
    @QAFragmentScope
    @Provides
    fun provideViewModelFactory(): QAQuestionDetailViewModelFactory
            = QAQuestionDetailViewModelFactory()
}
package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAAnswerDocViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class QAAnswerDocModule {
    @QAFragmentScope
    @Provides
    fun provideViewModelProvide(): QAAnswerDocViewModelFactory
            = QAAnswerDocViewModelFactory()
}
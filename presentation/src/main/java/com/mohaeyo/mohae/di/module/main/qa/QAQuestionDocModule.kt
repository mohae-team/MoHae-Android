package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.viewmodel.main.qa.questionDoc.QAQuestionDocViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class QAQuestionDocModule {
    @QAFragmentScope
    @Provides
    fun provideViewModeFactory(): QAQuestionDocViewModelFactory
            = QAQuestionDocViewModelFactory()
}
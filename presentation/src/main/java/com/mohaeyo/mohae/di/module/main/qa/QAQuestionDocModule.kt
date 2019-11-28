package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.domain.usecase.CreateQuestionUseCase
import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.viewmodel.main.qa.questionDoc.QAQuestionDocViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class QAQuestionDocModule {
    @QAFragmentScope
    @Provides
    fun provideViewModeFactory(
        createQuestionUseCase: CreateQuestionUseCase,
        questionMapper: QuestionMapper
    ): QAQuestionDocViewModelFactory
            = QAQuestionDocViewModelFactory(
        createQuestionUseCase,
        questionMapper
    )
}
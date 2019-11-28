package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.domain.usecase.GetQuestionListUseCase
import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.viewmodel.main.qa.questionList.QAQuestionListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class QAQuestionListModule {
    @QAFragmentScope
    @Provides
    fun provideViewModelFactory(
        getQuestionListUseCase: GetQuestionListUseCase,
        questionMapper: QuestionMapper
    ): QAQuestionListViewModelFactory
            = QAQuestionListViewModelFactory(
        getQuestionListUseCase,
        questionMapper
    )
}
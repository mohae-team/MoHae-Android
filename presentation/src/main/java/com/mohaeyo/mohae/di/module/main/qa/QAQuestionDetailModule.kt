package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.domain.usecase.GetQuestionDetailUseCase
import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAQuestionDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class QAQuestionDetailModule {
    @QAFragmentScope
    @Provides
    fun provideViewModelFactory(
        getQuestionDetailUseCase: GetQuestionDetailUseCase,
        questionMapper: QuestionMapper
    ): QAQuestionDetailViewModelFactory
            = QAQuestionDetailViewModelFactory(getQuestionDetailUseCase, questionMapper)
}
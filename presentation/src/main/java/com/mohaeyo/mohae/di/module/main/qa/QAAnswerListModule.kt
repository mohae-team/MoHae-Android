package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.domain.usecase.GetAnswerListUseCase
import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.mapper.AnswerMapper
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAAnswerListViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class QAAnswerListModule {
    @QAFragmentScope
    @Provides
    fun provideViewModelProvide(
        getAnswerListUseCase: GetAnswerListUseCase,
        answerMapper: AnswerMapper
    ): QAAnswerListViewModelFactory
            = QAAnswerListViewModelFactory(
        getAnswerListUseCase,
        answerMapper
    )
}
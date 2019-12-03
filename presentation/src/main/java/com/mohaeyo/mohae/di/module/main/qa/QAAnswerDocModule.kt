package com.mohaeyo.mohae.di.module.main.qa

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.CreateAnswerUseCase
import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.mapper.AnswerMapper
import com.mohaeyo.mohae.ui.fragment.main.qa.QAAnswerDocFragment
import com.mohaeyo.mohae.viewmodel.main.qa.answerDoc.QAAnswerDocViewModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAAnswerDocViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class QAAnswerDocModule {
    @QAFragmentScope
    @Provides
    fun provideViewModelProvide(
        createAnswerUseCase: CreateAnswerUseCase,
        answerMapper: AnswerMapper
    ): QAAnswerDocViewModelFactory
            = QAAnswerDocViewModelFactory(
        createAnswerUseCase,
        answerMapper
    )

    @QAFragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: QAAnswerDocViewModelFactory,
        fragment: QAAnswerDocFragment
    ): QAAnswerDocViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(QAAnswerDocViewModel::class.java)
}
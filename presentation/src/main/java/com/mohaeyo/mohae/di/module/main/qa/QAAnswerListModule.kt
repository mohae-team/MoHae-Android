package com.mohaeyo.mohae.di.module.main.qa

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.GetAnswerListUseCase
import com.mohaeyo.mohae.di.scope.QAFragmentScope
import com.mohaeyo.mohae.mapper.AnswerMapper
import com.mohaeyo.mohae.ui.fragment.main.qa.QAAnswerListFragment
import com.mohaeyo.mohae.viewmodel.main.qa.answerList.QAAnswerListViewModel
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

    @QAFragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: QAAnswerListViewModelFactory,
        fragment: QAAnswerListFragment
    ): QAAnswerListViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(QAAnswerListViewModel::class.java)
}
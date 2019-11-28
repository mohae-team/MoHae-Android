package com.mohaeyo.mohae.viewmodel.main.qa.questionDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetAnswerListUseCase
import com.mohaeyo.mohae.mapper.AnswerMapper

class QAAnswerListViewModelFactory(
    private val getAnswerListUseCase: GetAnswerListUseCase,
    private val answerMapper: AnswerMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetAnswerListUseCase::class.java,
        AnswerMapper::class.java
    ).newInstance(getAnswerListUseCase, answerMapper)
}
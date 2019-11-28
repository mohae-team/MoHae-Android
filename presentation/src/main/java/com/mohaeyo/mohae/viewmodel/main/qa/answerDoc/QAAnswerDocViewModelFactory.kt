package com.mohaeyo.mohae.viewmodel.main.qa.questionDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.CreateAnswerUseCase
import com.mohaeyo.mohae.mapper.AnswerMapper

class QAAnswerDocViewModelFactory(
    private val createAnswerUseCase: CreateAnswerUseCase,
    private val answerMapper: AnswerMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        CreateAnswerUseCase::class.java,
        AnswerMapper::class.java).newInstance(createAnswerUseCase, answerMapper)
}
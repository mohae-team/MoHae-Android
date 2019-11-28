package com.mohaeyo.mohae.viewmodel.main.qa.questionDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetQuestionDetailUseCase
import com.mohaeyo.mohae.mapper.QuestionMapper

class QAQuestionDetailViewModelFactory(
    private val getQuestionDetailUseCase: GetQuestionDetailUseCase,
    private val questionMapper: QuestionMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetQuestionDetailUseCase::class.java,
        QuestionMapper::class.java
    ).newInstance(getQuestionDetailUseCase, questionMapper)
}
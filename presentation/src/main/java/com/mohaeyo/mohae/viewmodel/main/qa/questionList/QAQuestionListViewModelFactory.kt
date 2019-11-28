package com.mohaeyo.mohae.viewmodel.main.qa.questionList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetQuestionListUseCase
import com.mohaeyo.mohae.mapper.QuestionMapper

class QAQuestionListViewModelFactory(
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val questionMapper: QuestionMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetQuestionListUseCase::class.java,
        QuestionMapper::class.java).newInstance(getQuestionListUseCase, questionMapper)
}
package com.mohaeyo.mohae.viewmodel.main.qa.questionDoc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.CreateQuestionUseCase
import com.mohaeyo.mohae.mapper.QuestionMapper

class QAQuestionDocViewModelFactory(
    private val createQuestionUseCase: CreateQuestionUseCase,
    private val questionMapper: QuestionMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        CreateQuestionUseCase::class.java,
        QuestionMapper::class.java
    ).newInstance(createQuestionUseCase, questionMapper)
}
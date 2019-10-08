package com.mohaeyo.mohae.viewmodel.main.qa.questionList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QAQuestionListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
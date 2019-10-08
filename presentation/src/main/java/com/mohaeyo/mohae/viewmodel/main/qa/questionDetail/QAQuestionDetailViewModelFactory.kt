package com.mohaeyo.mohae.viewmodel.main.qa.questionDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QAQuestionDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
package com.mohaeyo.mohae.viewmodel.main.feedback.doc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FeedbackDocViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
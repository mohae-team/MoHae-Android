package com.mohaeyo.mohae.viewmodel.main.feedback.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FeedbackDetailViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
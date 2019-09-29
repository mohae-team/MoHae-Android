package com.mohaeyo.mohae.viewmodel.main.feedback.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FeedbackListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
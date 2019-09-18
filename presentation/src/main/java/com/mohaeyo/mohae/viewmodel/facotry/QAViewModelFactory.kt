package com.mohaeyo.mohae.viewmodel.facotry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QAViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
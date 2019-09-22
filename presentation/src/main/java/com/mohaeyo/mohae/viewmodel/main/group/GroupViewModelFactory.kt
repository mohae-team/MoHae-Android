package com.mohaeyo.mohae.viewmodel.main.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroupViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
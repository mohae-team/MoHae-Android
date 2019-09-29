package com.mohaeyo.mohae.viewmodel.main.group.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroupDetailViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
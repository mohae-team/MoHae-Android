package com.mohaeyo.mohae.viewmodel.main.group.doc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroupDocViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
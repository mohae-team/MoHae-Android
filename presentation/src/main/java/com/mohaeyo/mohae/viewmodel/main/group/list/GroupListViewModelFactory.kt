package com.mohaeyo.mohae.viewmodel.main.group.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroupListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
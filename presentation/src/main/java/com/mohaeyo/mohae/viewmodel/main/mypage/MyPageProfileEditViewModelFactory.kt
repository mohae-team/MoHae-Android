package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyPageProfileEditViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
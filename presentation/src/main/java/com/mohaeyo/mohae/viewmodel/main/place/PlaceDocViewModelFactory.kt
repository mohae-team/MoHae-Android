package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlaceDocViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor().newInstance()
}
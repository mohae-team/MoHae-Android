package com.mohaeyo.mohae.viewmodel.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isValueBlank

class LoginViewModel: BaseViewModel() {

    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()

    val btnClickable = MediatorLiveData<Boolean>().apply {
        addSource(idText) { value = !idText.isValueBlank() && !passwordText.isValueBlank()  }
        addSource(passwordText) { value = !idText.isValueBlank() && !passwordText.isValueBlank() }
    }

    val startMainEvent = SingleLiveEvent<Unit>()
    val startSignUpEvent = SingleLiveEvent<Unit>()

    fun clickLogin() { startMainEvent.call() }

    fun clickSignUp() { startSignUpEvent.call() }
}
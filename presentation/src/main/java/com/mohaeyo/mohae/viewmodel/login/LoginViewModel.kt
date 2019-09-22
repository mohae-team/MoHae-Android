package com.mohaeyo.mohae.viewmodel.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isValueBlank

class LoginViewModel: BaseViewModel() {

    val emailText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()

    val btnClickable = MediatorLiveData<Boolean>().apply {
        addSource(emailText) { value = !emailText.isValueBlank() && !passwordText.isValueBlank() }
        addSource(passwordText) { value = !emailText.isValueBlank() && !passwordText.isValueBlank() }
    }

    val startMainEvent = SingleLiveEvent<Unit>()
    val startSignUpEvent = SingleLiveEvent<Unit>()

    fun clickLogin() { startMainEvent.call() }

    fun clickSignUp() { startSignUpEvent.call() }
}
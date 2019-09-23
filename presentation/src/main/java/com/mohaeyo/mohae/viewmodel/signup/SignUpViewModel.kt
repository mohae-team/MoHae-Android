package com.mohaeyo.mohae.viewmodel.signup

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isValueBlank

class SignUpViewModel: BaseViewModel() {

    val usernameText = MutableLiveData<String>()
    val emailText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()

    val btnClickable = MediatorLiveData<Boolean>().apply {
        addSource(usernameText) { value = !usernameText.isValueBlank() && !emailText.isValueBlank() && !passwordText.isValueBlank() }
        addSource(emailText) { value = !usernameText.isValueBlank() && !emailText.isValueBlank() && !passwordText.isValueBlank() }
        addSource(passwordText) { value = !usernameText.isValueBlank() && !emailText.isValueBlank() && !passwordText.isValueBlank() }
    }

    val startSignInEvent = SingleLiveEvent<Unit>()

    fun clickSignUp() { startSignInEvent.call() }
}
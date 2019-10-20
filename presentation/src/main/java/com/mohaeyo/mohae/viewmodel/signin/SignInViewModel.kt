package com.mohaeyo.mohae.viewmodel.signin

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isValueBlank

class SignInViewModel: BaseViewModel() {

    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()

    val btnClickable = MediatorLiveData<Boolean>().apply {
        addSource(idText) { value = !idText.isValueBlank() && !passwordText.isValueBlank()  }
        addSource(passwordText) { value = !idText.isValueBlank() && !passwordText.isValueBlank() }
    }

    val startMainEvent = SingleLiveEvent<Unit>()
    val startSignUpEvent = SingleLiveEvent<Unit>()
    val idErrorEvent = SingleLiveEvent<String>()
    val passwordErrorEvent = SingleLiveEvent<String>()

    fun clickLogin() { startMainEvent.call() }

    fun clickSignUp() { startSignUpEvent.call() }
}
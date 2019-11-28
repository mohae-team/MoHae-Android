package com.mohaeyo.mohae.viewmodel.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.SignUpUseCase

class SignUpViewModelFactory(val signUpUseCase: SignUpUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(SignUpUseCase::class.java).newInstance(signUpUseCase)

}
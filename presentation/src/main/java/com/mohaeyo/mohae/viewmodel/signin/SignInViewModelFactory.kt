package com.mohaeyo.mohae.viewmodel.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.SignInUseCase

class SignInViewModelFactory(val signInUseCase: SignInUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(SignInUseCase::class.java).newInstance(signInUseCase)

}
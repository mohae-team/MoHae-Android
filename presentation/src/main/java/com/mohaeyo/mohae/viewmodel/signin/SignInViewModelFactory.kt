package com.mohaeyo.mohae.viewmodel.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.SignInUseCase
import com.mohaeyo.mohae.mapper.AuthMapper

class SignInViewModelFactory(
    private val signInUseCase: SignInUseCase,
    private val authMapper: AuthMapper): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        SignInUseCase::class.java,
        AuthMapper::class.java).newInstance(signInUseCase, authMapper)

}
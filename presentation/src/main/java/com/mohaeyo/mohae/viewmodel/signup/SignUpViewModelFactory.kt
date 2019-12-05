package com.mohaeyo.mohae.viewmodel.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.SignUpUseCase
import com.mohaeyo.mohae.mapper.UserMapper

class SignUpViewModelFactory(
    private val signUpUseCase: SignUpUseCase,
    private val userMapper: UserMapper): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        SignUpUseCase::class.java,
        UserMapper::class.java).newInstance(signUpUseCase, userMapper)

}
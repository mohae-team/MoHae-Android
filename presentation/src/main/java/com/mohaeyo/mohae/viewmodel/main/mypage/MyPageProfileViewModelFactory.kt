package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.mapper.UserMapper

class MyPageProfileViewModelFactory(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val userMapper: UserMapper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetUserProfileUseCase::class.java,
        UserMapper::class.java
    ).newInstance(getUserProfileUseCase, userMapper)
}
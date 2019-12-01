package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.mapper.UserMapper

class MyPageProfileEditViewModelFactory(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val editUserProfileUseCase: EditUserProfileUseCase,
    private val userMapper: UserMapper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(GetUserProfileUseCase::class.java,
        EditUserProfileUseCase::class.java,
        UserMapper::class.java
    ).newInstance(getUserProfileUseCase, editUserProfileUseCase, userMapper)
}
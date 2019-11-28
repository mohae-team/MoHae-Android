package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.mapper.ProfileMapper

class MyPageProfileEditViewModelFactory(val getUserProfileUseCase: GetUserProfileUseCase,
                                        val editUserProfileUseCase: EditUserProfileUseCase,
                                        val profileMapper: ProfileMapper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(GetUserProfileUseCase::class.java,
        EditUserProfileUseCase::class.java, ProfileMapper::class.java)
        .newInstance(getUserProfileUseCase, editUserProfileUseCase, profileMapper)
}
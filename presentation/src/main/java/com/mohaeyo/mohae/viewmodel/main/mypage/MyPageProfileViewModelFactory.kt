package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.mapper.ProfileMapper

class MyPageProfileViewModelFactory(val getUserProfileUseCase: GetUserProfileUseCase,
                                    val profileMapper: ProfileMapper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(GetUserProfileUseCase::class.java,
        ProfileMapper::class.java).newInstance(getUserProfileUseCase, profileMapper)
}
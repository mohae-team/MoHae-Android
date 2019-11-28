package com.mohaeyo.mohae.di.module.main.mypage

import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.di.scope.MyPageFragmentScope
import com.mohaeyo.mohae.mapper.ProfileMapper
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class MyPageProfileEditModule {
    @MyPageFragmentScope
    @Provides
    fun provideViewModelFactory(getUserProfileUseCase: GetUserProfileUseCase,
                                editUserProfileUseCase: EditUserProfileUseCase,
                                profileMapper: ProfileMapper
    ): MyPageProfileEditViewModelFactory
            = MyPageProfileEditViewModelFactory(getUserProfileUseCase, editUserProfileUseCase, profileMapper)
}
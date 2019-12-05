package com.mohaeyo.mohae.di.module.main.mypage

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.di.scope.MyPageFragmentScope
import com.mohaeyo.mohae.mapper.UserMapper
import com.mohaeyo.mohae.ui.fragment.main.mypage.MyPageProfileEditFragment
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class MyPageProfileEditModule {
    @MyPageFragmentScope
    @Provides
    fun provideViewModelFactory(getUserProfileUseCase: GetUserProfileUseCase,
                                editUserProfileUseCase: EditUserProfileUseCase,
                                userMapper: UserMapper
    ): MyPageProfileEditViewModelFactory
            = MyPageProfileEditViewModelFactory(getUserProfileUseCase, editUserProfileUseCase, userMapper)

    @MyPageFragmentScope
    @Provides
    fun provideViewModel(viewModelFactory: MyPageProfileEditViewModelFactory,
                         fragment: MyPageProfileEditFragment
    ): MyPageProfileEditViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(MyPageProfileEditViewModel::class.java)
}
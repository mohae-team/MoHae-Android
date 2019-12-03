package com.mohaeyo.mohae.di.module.main.mypage

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.di.scope.MyPageFragmentScope
import com.mohaeyo.mohae.mapper.UserMapper
import com.mohaeyo.mohae.ui.fragment.main.mypage.MyPageFragment
import com.mohaeyo.mohae.ui.fragment.main.mypage.MyPageProfileFragment
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MyPageProfileModule {
    @MyPageFragmentScope
    @Provides
    fun provideViewModelFactory(getUserProfileUseCase: GetUserProfileUseCase,
                                userMapper: UserMapper
    ): MyPageProfileViewModelFactory
            = MyPageProfileViewModelFactory(getUserProfileUseCase, userMapper)

    @MyPageFragmentScope
    @Provides
    fun provideViewModel(viewModelFactory: MyPageProfileViewModelFactory,
                         fragment: MyPageProfileFragment
    ): MyPageProfileViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(MyPageProfileViewModel::class.java)
}
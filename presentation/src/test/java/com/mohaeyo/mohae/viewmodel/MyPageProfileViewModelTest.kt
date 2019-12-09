package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.UserMapper
import com.mohaeyo.mohae.model.UserModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MyPageProfileViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getUserProfileUseCase: GetUserProfileUseCase

    private lateinit var myPageProfileViewModel: MyPageProfileViewModel
    private lateinit var userMapper: UserMapper

    @Before
    fun init() {
        userMapper = UserMapper()
        myPageProfileViewModel =
            MyPageProfileViewModel(getUserProfileUseCase, userMapper)
    }

    @Test
    fun `유저정보 가져오는 행위 성공 알림 테스트`() {
        myPageProfileViewModel.run {
            val result = UserModel(id = "jinwoo")
            getSuccess(result)
            userModel.test().assertValue(result)
        }
    }

    @Test
    fun `유저정보 가져오는 행위 실패 알림 테스트`() {
        myPageProfileViewModel.run {
            val failMessage = "유저정보를 가져올 수 없습니다."
            getFail(failMessage)
            createToastEvent.test().assertValue(failMessage)
        }
    }

    @Test
    fun `로그아웃 테스트`() {
        myPageProfileViewModel.run {
            clickLogout()
            startLoginEvent.test().assertHasValue()
        }
    }
}
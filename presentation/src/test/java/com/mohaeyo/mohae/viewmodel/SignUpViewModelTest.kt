package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.SignUpUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.UserMapper
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class SignUpViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var signUpUseCase: SignUpUseCase

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var userMapper: UserMapper

    @Before
    fun init() {
        userMapper = UserMapper()
        signUpViewModel =
            SignUpViewModel(signUpUseCase, userMapper)
    }

    @Test
    fun `회원가입 다음 버튼 클릭 가능 테스트`() {
        signUpViewModel.run {
            usernameText.value = "jinwoo"
            idText.value = "jinwoo123"
            passwordText.value = "jinwoo123!!!"
            passwordCheckText.value = "jinwoo123!!!"

            nextBtnClickable.test().assertValue(true)
        }
    }

    @Test
    fun `회원가입 다음 버튼 클릭 불가능 테스트`() {
        signUpViewModel.run {
            usernameText.value = "jinwoo"
            idText.value = "jinwoo123"
            passwordText.value = "jinwoo123!!!"
            passwordText.value = "jinwoo123!!"

            nextBtnClickable.test().assertValue(false)
        }
    }

    @Test
    fun `회원가입 완료 버튼 클릭 가능 테스트`() {
        signUpViewModel.run {
            addressText.value = "강남구"

            completeBtnClickable.test().assertValue(true)
        }
    }

    @Test
    fun `회원가입 완료 버튼 클릭 불가능 테스트`() {
        signUpViewModel.run {
            addressText.value = "거주 지역을 선택해주세요"

            completeBtnClickable.test().assertValue(false)
        }
    }

    @Test
    fun `회원가입 성공 알림 테스트`() {
        signUpViewModel.run {
            signUpSuccess()

            startSignInEvent.test().assertHasValue()
        }
    }

    @Test
    fun `회원가입 실패 알림 테스트`() {
        signUpViewModel.run {
            val failMessage = "이미 존재하는 아이디입니다."

            signUpFail(failMessage)

            createToastEvent.test().assertHasValue()
        }
    }
}
package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.SignInUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.AuthMapper
import com.mohaeyo.mohae.viewmodel.signin.SignInViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class SignInViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var signInUseCase: SignInUseCase

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var authMapper: AuthMapper

    @Before
    fun init() {
        authMapper = AuthMapper()
        signInViewModel =
            SignInViewModel(signInUseCase, authMapper)
    }

    @Test
    fun `로그인 버튼 클릭 가능 테스트`() {
        signInViewModel.run {
            idText.value = "jinwoo123"
            passwordText.value = "jinwoo123!!!"

            btnClickable.test().assertValue(true)
        }
    }

    @Test
    fun `로그인 버튼 클릭 불가능 테스트`() {
        signInViewModel.run {
            idText.value = "jinwoo123"

            btnClickable.test().assertValue(false)
        }
    }

    @Test
    fun `로그인 성공 알림 테스트`() {
        signInViewModel.run {
            loginSuccess()

            startMainEvent.test().assertHasValue()
        }
    }

    @Test
    fun `로그인 실패 알림 테스트`() {
        signInViewModel.run {
            val failMessage = "존재하지 않는 유저입니다."

            loginFail(failMessage)

            idErrorEvent.test().assertValue(failMessage)
            passwordErrorEvent.test().assertValue(failMessage)
        }
    }
}
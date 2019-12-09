package com.mohaeyo.domain.service

import com.mohaeyo.domain.BaseTest
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.handler.AuthErrorHandler
import com.mohaeyo.domain.repository.AuthRepository
import com.mohaeyo.domain.usecase.SignInUseCase
import com.mohaeyo.domain.usecase.SignUpUseCase
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class AuthServiceTest: BaseTest() {
    @Mock
    private lateinit var authRepository: AuthRepository
    @Mock
    private lateinit var authErrorHandler: AuthErrorHandler

    private lateinit var authService: AuthService

    private lateinit var signInUseCase: SignInUseCase
    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun init() {
        authService = AuthServiceImpl(authRepository, authErrorHandler)

        signInUseCase = SignInUseCase(authService, CompositeDisposable())
        signUpUseCase = SignUpUseCase(authService, CompositeDisposable())
    }

    @Test
    fun `로그인 성공 테스트`() {
        `when`(authRepository.signIn(AuthEntity()))
            .thenReturn(Flowable.just(TokenEntity()))

        signInUseCase.createFlowable(
            AuthEntity()
        ).test().assertValue(TokenEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `로그인 실패 테스트`() {
        val throwable = Throwable()

        `when`(authRepository.signIn(AuthEntity()))
            .thenReturn(Flowable.error(throwable))

        `when`(authRepository.getToken()).thenReturn("")

        `when`(authErrorHandler.signInErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        signInUseCase.createFlowable(
            AuthEntity()
        ).test().assertValue(TokenEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `회원가입 성공 테스트`() {
        `when`(authRepository.signUp(UserEntity()))
            .thenReturn(Flowable.just(TokenEntity()))

        signUpUseCase.createFlowable(
            UserEntity()
        ).test().assertValue(TokenEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `회원가입 실패 테스트`() {
        val throwable = Throwable()

        `when`(authRepository.signUp(UserEntity()))
            .thenReturn(Flowable.error(throwable))

        `when`(authRepository.getToken()).thenReturn("")

        `when`(authErrorHandler.signUpErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        signUpUseCase.createFlowable(
            UserEntity()
        ).test().assertValue(TokenEntity() to ErrorHandlerEntity(isSuccess = false))
    }
}
package com.mohaeyo.domain.service

import com.mohaeyo.domain.*
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.handler.UserErrorHandler
import com.mohaeyo.domain.repository.UserRepository
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.amshove.kluent.When
import org.amshove.kluent.that
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class UserServiceTest: BaseTest() {
    @Mock
    private lateinit var userRepository: UserRepository
    @Mock
    private lateinit var userErrorHandler: UserErrorHandler

    private lateinit var userService: UserService

    private lateinit var getUserProfileUseCase: GetUserProfileUseCase
    private lateinit var editUserProfileUseCase: EditUserProfileUseCase

    @Before
    fun init() {
        userService = UserServiceImpl(userRepository, userErrorHandler)

        getUserProfileUseCase = GetUserProfileUseCase(userService, CompositeDisposable())
        editUserProfileUseCase = EditUserProfileUseCase(userService, CompositeDisposable())
    }

    @Test
    fun `유저정보 가져오기 성공 테스트`() {
        `when`(userRepository.getRemoteUser())
            .thenReturn(Flowable.just(UserEntity()))

        getUserProfileUseCase.createFlowable(
            Unit
        ).test().assertValue(UserEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `유저정보 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(userRepository.getRemoteUser())
            .thenReturn(Flowable.error(throwable))

        `when`(userRepository.getLocalUser()).thenReturn(UserEntity())

        doNothing().`when`(userRepository).saveLocalUser(UserEntity())

        `when`(userErrorHandler.getInfoErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getUserProfileUseCase.createFlowable(
            Unit
        ).test().assertValue(UserEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `유저정보 수정하기 성공 테스트`() {
        `when`(userRepository.postRemoteUser(UserEntity()))
            .thenReturn(Flowable.just(UserEntity()))

        editUserProfileUseCase.createFlowable(
            UserEntity()
        ).test().assertValue(UserEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `유저정보 수정하기 실패 테스트`() {
        val throwable = Throwable()

        `when`(userRepository.postRemoteUser(
            UserEntity()
        )).thenReturn(Flowable.error(throwable))

        `when`(userRepository.getLocalUser())
            .thenReturn(UserEntity())


        `when` (userErrorHandler.editInfoErrorHandle(
            throwable
        )).thenReturn(ErrorHandlerEntity(isSuccess = false))


        editUserProfileUseCase.createFlowable(
            UserEntity()
        ).test().assertValue(UserEntity() to ErrorHandlerEntity(isSuccess = false))
    }
}
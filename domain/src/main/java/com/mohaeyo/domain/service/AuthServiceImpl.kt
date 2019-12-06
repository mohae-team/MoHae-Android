package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.handler.AuthErrorHandler
import com.mohaeyo.domain.repository.AuthRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val authErrorHandler: AuthErrorHandler): AuthService {
    override fun signIn(auth: AuthEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
            = authRepository.signIn(auth).map {
        TokenEntity(it.token) to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        authRepository.saveToken(it.first.token)
    }.onErrorReturn {
        TokenEntity(authRepository.getToken()) to authErrorHandler.signInErrorHandle(it)
    }

    override fun signUp(user: UserEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
            = authRepository.signUp(user).map {
        TokenEntity(it.token) to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        authRepository.saveToken(it.first.token)
    }.onErrorReturn {
        TokenEntity(authRepository.getToken()) to authErrorHandler.signUpErrorHandle(it)
    }
}
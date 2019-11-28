package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.repository.AuthRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class AuthServiceImpl(private val authRepository: AuthRepository): AuthService {
    override fun signIn(auth: AuthEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
            = authRepository.signIn(auth).map {
        TokenEntity(it.token) to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        authRepository.saveToken(it.first.token)
    }.onErrorReturn {
        if (it is HttpException)
            TokenEntity(authRepository.getToken()) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 회원정보입니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else TokenEntity(authRepository.getToken()) to ErrorHandlerEntity(isSuccess = true)
    }

    override fun signUp(user: UserEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
            = authRepository.signUp(user).map {
        TokenEntity(it.token) to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        authRepository.saveToken(it.first.token)
    }.onErrorReturn {
        if (it is HttpException)
            TokenEntity(authRepository.getToken()) to ErrorHandlerEntity(message =
            when(it.code()) {
                409 -> "이미 존재하는 회원정보입니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
         else TokenEntity(authRepository.getToken()) to ErrorHandlerEntity(isSuccess = true)
    }
}
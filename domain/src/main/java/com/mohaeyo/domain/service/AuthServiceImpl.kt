package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.repository.AuthRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class AuthServiceImpl(private val authRepository: AuthRepository): AuthService {
    override fun signIn(auth: AuthEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
            = authRepository.signIn(auth).map {
        TokenEntity(it.token) to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        authRepository.saveToken(it.first.token)
    }.onErrorReturn {
        TokenEntity(authRepository.getToken()) to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 회원정보입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun signUp(user: UserEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
            = authRepository.signUp(user).map {
        TokenEntity(it.token) to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        authRepository.saveToken(it.first.token)
    }.onErrorReturn {
        TokenEntity(authRepository.getToken()) to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                409 -> ErrorHandlerEntity(message = "이미 존재하는 회원정보입니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }
}
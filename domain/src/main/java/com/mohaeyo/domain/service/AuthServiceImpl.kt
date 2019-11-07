package com.mohaeyo.domain.service

import android.media.session.MediaSession
import android.util.Log
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.repository.AuthRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class AuthServiceImpl(private val authRepository: AuthRepository): AuthService {
    override fun signIn(auth: AuthEntity): Flowable<TokenEntity>
            = authRepository.signIn(auth).map {
        authRepository.saveToken(it)
        TokenEntity(it, true)
    }.onErrorReturn {
        if (it is HttpException) {
            TokenEntity(
            when(it.code()) {
                404 -> "존재하지 않는 회원정보입니다."
                else -> "네트워크 상태를 확인해주세요"
            }, false)
        }
        else TokenEntity(authRepository.getToken(), true)
    }

    override fun signUp(user: UserEntity): Flowable<TokenEntity>
            = authRepository.signUp(user).map {
        authRepository.saveToken(it)
        TokenEntity(it, true)
    }.onErrorReturn {
        if (it is HttpException) {
            TokenEntity(
                when(it.code()) {
                    409 -> "이미 존재하는 회원정보입니다"
                    else -> "네트워크 상태를 확인해주세요"
                }, false)
        }
        else TokenEntity(authRepository.getToken(), true)
    }
}
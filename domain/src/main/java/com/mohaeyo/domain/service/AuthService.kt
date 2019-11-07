package com.mohaeyo.domain.service

import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import io.reactivex.Flowable

interface AuthService {
    fun signUp(user: UserEntity): Flowable<TokenEntity>

    fun signIn(auth: AuthEntity): Flowable<TokenEntity>
}
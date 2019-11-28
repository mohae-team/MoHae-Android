package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import io.reactivex.Flowable

interface AuthService {
    fun signUp(user: UserEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>

    fun signIn(auth: AuthEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
}
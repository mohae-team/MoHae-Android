package com.mohaeyo.domain.repository

import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import io.reactivex.Flowable

interface AuthRepository {
    fun signIn(auth: AuthEntity): Flowable<TokenEntity>
    fun signUp(user: UserEntity): Flowable<TokenEntity>
    fun saveToken(token: String)
    fun getToken(): String
}
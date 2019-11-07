package com.mohaeyo.domain.repository

import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.UserEntity
import io.reactivex.Flowable

interface AuthRepository {
    fun signIn(auth: AuthEntity): Flowable<String>
    fun signUp(user: UserEntity): Flowable<String>
    fun saveToken(token: String)
    fun getToken(): String
}
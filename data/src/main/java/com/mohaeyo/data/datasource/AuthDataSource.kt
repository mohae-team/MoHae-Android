package com.mohaeyo.data.datasource

import com.mohaeyo.data.entity.AuthData
import com.mohaeyo.data.entity.UserData
import io.reactivex.Flowable

interface AuthDataSource {
    fun postRemoteSignIn(auth: AuthData): Flowable<String>

    fun postRemoteSignUp(user: UserData): Flowable<String>

    fun saveToken(token: String)

    fun getToken(): String
}
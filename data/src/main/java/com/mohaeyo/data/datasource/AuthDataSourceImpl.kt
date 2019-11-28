package com.mohaeyo.data.datasource

import com.mohaeyo.data.entity.AuthData
import com.mohaeyo.data.entity.TokenData
import com.mohaeyo.data.entity.UserData
import com.mohaeyo.data.local.pref.LocalStorage
import com.mohaeyo.data.remote.Api
import io.reactivex.Flowable

class AuthDataSourceImpl(val api: Api, val pref: LocalStorage): AuthDataSource {
    override fun postRemoteSignIn(auth: AuthData): Flowable<TokenData>
            = api.signIn(auth)

    override fun postRemoteSignUp(user: UserData): Flowable<TokenData>
            = api.signUp(user)

    override fun saveToken(token: String)
            = pref.saveToken(token, true)

    override fun getToken(): String
            = pref.getToken(true)
}
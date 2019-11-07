package com.mohaeyo.data.repository

import com.mohaeyo.data.datasource.AuthDataSource
import com.mohaeyo.data.entity.AuthData
import com.mohaeyo.data.entity.UserData
import com.mohaeyo.data.mapper.AuthDataMapper
import com.mohaeyo.data.mapper.UserDataMapper
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.repository.AuthRepository
import io.reactivex.Flowable

class AuthRepositoryImpl(private val datasource: AuthDataSource,
                         private val authDataMapper: AuthDataMapper,
                         private val userDataMapper: UserDataMapper): AuthRepository {

    private val mapAuthEntityToData: (AuthEntity) -> AuthData
            = { authDataMapper.mapFrom(it) }

    private val mapUserEntityToData: (UserEntity) -> UserData
            = { userDataMapper.mapFrom(it) }

    override fun signIn(auth: AuthEntity): Flowable<String>
            = datasource.postRemoteSignIn(mapAuthEntityToData(auth))

    override fun signUp(user: UserEntity): Flowable<String>
            = datasource.postRemoteSignUp(mapUserEntityToData(user))

    override fun saveToken(token: String)
            = datasource.saveToken(token)

    override fun getToken(): String
            = datasource.getToken()
}
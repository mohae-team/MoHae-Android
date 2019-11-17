package com.mohaeyo.domain.repository

import com.mohaeyo.domain.entity.UserEntity
import io.reactivex.Flowable

interface UserRepository {
    fun getRemoteUser(): Flowable<UserEntity>
    fun postRemoteUser(user: UserEntity): Flowable<UserEntity>
    fun getLocalUser(): UserEntity
    fun saveLocalUser(user: UserEntity): Flowable<UserEntity>
}
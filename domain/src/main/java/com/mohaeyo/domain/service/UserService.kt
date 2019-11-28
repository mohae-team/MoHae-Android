package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import io.reactivex.Flowable

interface UserService {
    fun getUserProfile(): Flowable<Pair<UserEntity, ErrorHandlerEntity>>

    fun editUserProfile(user: UserEntity): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
}
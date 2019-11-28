package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.UserDto
import com.mohaeyo.data.entity.UserData
import com.mohaeyo.data.local.database.entity.User
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface UserDataSource {
    fun getRemoteUser(): Flowable<UserDto>

    fun saveLocalUser(user: User)

    fun postRemoteUser(user: UserData): Flowable<UserDto>

    fun getLocalUser(): User
}
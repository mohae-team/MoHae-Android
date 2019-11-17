package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.UserDto
import com.mohaeyo.data.entity.UserData
import com.mohaeyo.data.local.database.dao.UserDao
import com.mohaeyo.data.local.database.entity.User
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.toImageRequestBody
import io.reactivex.Flowable
import okhttp3.MultipartBody

class UserDataSourceImpl(private val api: Api, private val userDao: UserDao): UserDataSource {
    override fun getRemoteUser(): Flowable<UserDto>
            = api.getProfile()

    override fun saveLocalUser(user: User): Flowable<User> {
        return Flowable.just(user).map {
            userDao.saveUser(it)
            return@map it
        }
    }

    override fun postRemoteUser(user: UserData): Flowable<UserDto>
            = api.editProfile(
            username = MultipartBody.Part.createFormData("username", user.username),
            address = MultipartBody.Part.createFormData("address", user.address),
            description = MultipartBody.Part.createFormData("description", user.description),
            imageFile = MultipartBody.Part.createFormData("imageFile", user.imageFile.name, user.imageFile.toImageRequestBody()))

    override fun getLocalUser(): User
            = userDao.getUser()
}
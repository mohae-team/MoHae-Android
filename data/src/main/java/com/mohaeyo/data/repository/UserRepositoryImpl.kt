package com.mohaeyo.data.repository

import com.mohaeyo.data.datasource.UserDataSource
import com.mohaeyo.data.dto.UserDto
import com.mohaeyo.data.entity.UserData
import com.mohaeyo.data.local.database.entity.User
import com.mohaeyo.data.mapper.UserDataMapper
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.repository.UserRepository
import io.reactivex.Flowable

class UserRepositoryImpl(private val datasource: UserDataSource,
                         private val userDataMapper: UserDataMapper) : UserRepository {

    override fun getRemoteUser(): Flowable<UserEntity>
            = datasource.getRemoteUser().map { userDataMapper.mapDtoToEntity(it) }

    override fun postRemoteUser(user: UserEntity): Flowable<UserEntity>
            = datasource.postRemoteUser(userDataMapper.mapFrom(user))
        .map { userDataMapper.mapDtoToEntity(it) }

    override fun getLocalUser(): UserEntity
            = userDataMapper.mapDbToEntity(datasource.getLocalUser())

    override fun saveLocalUser(user: UserEntity)
            = datasource.saveLocalUser(userDataMapper.mapEntityToDb(user))
}
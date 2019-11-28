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
    val mapUserEntityToData: (UserEntity) -> UserData
            = { userDataMapper.mapFrom(it) }

    val mapUserDtoToEntity: (UserDto) -> UserEntity
            = { userDataMapper.mapDtoToEntity(it) }

    val mapUserDbToEntity: (User) -> UserEntity
            = { userDataMapper.mapDbToEntity(it) }

    val mapUserEntityToDb: (UserEntity) -> User
            = { userDataMapper.mapEntityToDb(it) }

    override fun getRemoteUser(): Flowable<UserEntity>
            = datasource.getRemoteUser().map { mapUserDtoToEntity(it) }

    override fun postRemoteUser(user: UserEntity): Flowable<UserEntity>
            = datasource.postRemoteUser(mapUserEntityToData(user))
        .map { mapUserDtoToEntity(it) }

    override fun getLocalUser(): UserEntity
            = mapUserDbToEntity(datasource.getLocalUser())

    override fun saveLocalUser(user: UserEntity)
            = datasource.saveLocalUser(mapUserEntityToDb(user))
}
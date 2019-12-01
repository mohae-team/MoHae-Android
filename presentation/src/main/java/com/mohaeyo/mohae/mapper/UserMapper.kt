package com.mohaeyo.mohae.mapper

import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.mapper.Mapper
import com.mohaeyo.mohae.model.UserModel

class UserMapper: Mapper<UserModel, UserEntity> {
    override fun mapFrom(from: UserModel): UserEntity
            = UserEntity(
        id = from.id,
        password = from.password,
        username = from.username,
        imageFile = from.imageFile,
        address = from.address,
        description = from.description
    )

    fun mapEntityToModel(from: UserEntity): UserModel
            = UserModel(
        id = from.id,
        password = from.password,
        username = from.username,
        imageFile = from.imageFile,
        address = from.address,
        description = from.description
    )
}
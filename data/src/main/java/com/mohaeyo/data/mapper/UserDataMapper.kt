package com.mohaeyo.data.mapper

import com.mohaeyo.data.dto.UserDto
import com.mohaeyo.data.entity.UserData
import com.mohaeyo.data.local.database.entity.User
import com.mohaeyo.data.toFile
import com.mohaeyo.data.toImageRequestBody
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.mapper.Mapper

class UserDataMapper: Mapper<UserEntity, UserData> {
    override fun mapFrom(from: UserEntity): UserData
            = UserData(
        id = from.id,
        password = from.password,
        username = from.username,
        imageFile = from.imageFile,
        address = from.address,
        description = from.description
    )

    fun mapDtoToEntity(from: UserDto): UserEntity
            = UserEntity(
        id = from.id,
        password = from.password,
        username = from.username,
        imageFile = from.imageUri.toFile(),
        address = from.address,
        description = from.description
    )

    fun mapEntityToDb(from: UserEntity): User
            = User(
        id = from.id,
        password = from.password,
        username = from.username,
        imageUri = from.imageFile.toString(),
        address = from.address,
        description = from.description
    )

    fun mapDbToEntity(from: User): UserEntity
            = UserEntity(
        id = from.id,
        password = from.password,
        username = from.username,
        imageFile = from.imageUri.toFile(),
        address = from.address,
        description = from.description
    )
}
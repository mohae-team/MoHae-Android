package com.mohaeyo.data.mapper

import com.mohaeyo.data.entity.UserData
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.mapper.Mapper

class UserDataMapper: Mapper<UserEntity, UserData> {
    override fun mapFrom(from: UserEntity): UserData
            = UserData(
        id = from.id,
        password = from.password,
        username = from.username,
        imageByteList = from.imageByteList,
        address = from.address,
        description = from.description
    )
}
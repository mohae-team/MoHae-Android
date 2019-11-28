package com.mohaeyo.data.mapper

import com.mohaeyo.data.entity.AuthData
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.mapper.Mapper

class AuthDataMapper: Mapper<AuthEntity, AuthData> {
    override fun mapFrom(from: AuthEntity): AuthData
            = AuthData(
        id = from.id,
        password = from.password
    )
}
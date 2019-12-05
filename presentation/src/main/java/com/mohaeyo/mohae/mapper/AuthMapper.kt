package com.mohaeyo.mohae.mapper

import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.mapper.Mapper
import com.mohaeyo.mohae.model.AuthModel

class AuthMapper: Mapper<AuthModel, AuthEntity> {
    override fun mapFrom(from: AuthModel): AuthEntity
            = AuthEntity(
        id = from.id,
        password = from.password
    )
}
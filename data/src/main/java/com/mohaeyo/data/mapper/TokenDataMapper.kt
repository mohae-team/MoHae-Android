package com.mohaeyo.data.mapper

import com.mohaeyo.data.entity.TokenData
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.mapper.Mapper

class TokenDataMapper: Mapper<TokenData, TokenEntity> {
    override fun mapFrom(from: TokenData): TokenEntity
            = TokenEntity(
        token = from.token
    )
}
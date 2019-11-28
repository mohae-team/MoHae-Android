package com.mohaeyo.mohae.mapper

import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.mapper.Mapper
import com.mohaeyo.mohae.model.ProfileModel

class ProfileMapper: Mapper<UserEntity, ProfileModel> {
    override fun mapFrom(from: UserEntity): ProfileModel
            = ProfileModel(
        id = from.id,
        name = from.username,
        imageFile = from.imageFile,
        address = from.address,
        description = from.description
    )
}
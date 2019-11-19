package com.mohaeyo.mohae.mapper

import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.mapper.Mapper
import com.mohaeyo.mohae.model.PlaceModel

class PlaceMapper: Mapper<PlaceEntity, PlaceModel> {
    override fun mapFrom(from: PlaceEntity): PlaceModel
            = PlaceModel(
        name = from.placeName,
        location = from.location,
        likeCount = from.likeCount,
        isLike = from.isLike,
        description = from.description
    )

    fun mapModelToEntity(from: PlaceModel): PlaceEntity
            = PlaceEntity(
        placeName = from.name,
        location = from.location,
        likeCount = from.likeCount,
        isLike = from.isLike,
        description = from.description
    )
}
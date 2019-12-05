package com.mohaeyo.data.mapper

import com.mohaeyo.data.entity.PlaceData
import com.mohaeyo.data.local.database.entity.Place
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.mapper.Mapper

class PlaceDataMapper: Mapper<PlaceEntity, PlaceData> {
    override fun mapFrom(from: PlaceEntity): PlaceData
            = PlaceData(
        placeName = from.placeName,
        location = from.location,
        description = from.description,
        likeCount = from.likeCount,
        isLike = from.isLike
    )

    fun mapDataToEntity(from: PlaceData): PlaceEntity
            = PlaceEntity(
        placeName = from.placeName,
        location = from.location,
        description = from.description,
        likeCount = from.likeCount,
        isLike = from.isLike
    )
    fun mapEntityToDb(from: PlaceEntity): Place
            = Place(
        placeName = from.placeName,
        location = from.location,
        description = from.description,
        likeCount = from.likeCount,
        isLike = from.isLike
    )

    fun mapDbToEntity(from: Place): PlaceEntity
            = PlaceEntity(
        placeName = from.placeName,
        location = from.location,
        description = from.description,
        likeCount = from.likeCount,
        isLike = from.isLike
    )
}
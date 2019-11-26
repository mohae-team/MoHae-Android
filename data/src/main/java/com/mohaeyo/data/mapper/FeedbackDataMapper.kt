package com.mohaeyo.data.mapper

import com.mohaeyo.data.dto.FeedbackDto
import com.mohaeyo.data.entity.FeedbackData
import com.mohaeyo.data.local.database.entity.Feedback
import com.mohaeyo.data.toFile
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.mapper.Mapper

class FeedbackDataMapper: Mapper<FeedbackEntity, FeedbackData> {
    override fun mapFrom(from: FeedbackEntity): FeedbackData
            = FeedbackData(
        id = from.id,
        address = from.address,
        location = from.location,
        imageFile = from.imageFile,
        description = from.description,
        summary = from.summary,
        hateCount = from.hateCount,
        likeCount = from.likeCount,
        placeName = from.placeName,
        isLike = from.isLike,
        isHate = from.isHate
    )

    fun mapDbToEntity(from: Feedback): FeedbackEntity
            = FeedbackEntity(
        id = from.id,
        address = from.address,
        location = from.location,
        imageFile = from.imageUri.toFile(),
        description = from.description,
        summary = from.summary,
        hateCount = from.hateCount,
        likeCount = from.likeCount,
        placeName = from.placeName,
        isLike = from.isLike,
        isHate = from.isHate
    )

    fun mapEntityToDb(from: FeedbackEntity): Feedback
            = Feedback(
        id = from.id,
        address = from.address,
        location = from.location,
        imageUri = from.imageFile.toString(),
        description = from.description,
        summary = from.summary,
        hateCount = from.hateCount,
        likeCount = from.likeCount,
        placeName = from.placeName,
        isLike = from.isLike,
        isHate = from.isHate
    )

    fun mapDtoToEntity(from: FeedbackDto): FeedbackEntity
            = FeedbackEntity(
        id = from.id,
        address = from.address,
        location = from.location,
        imageFile = from.imageUri.toFile(),
        description = from.description,
        summary = from.summary,
        hateCount = from.hateCount,
        likeCount = from.likeCount,
        placeName = from.placeName,
        isLike = from.isLike,
        isHate = from.isHate
    )
}
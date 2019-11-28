package com.mohaeyo.mohae.mapper

import com.mohaeyo.data.toFile
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.mapper.Mapper
import com.mohaeyo.mohae.model.FeedbackModel

class FeedbackMapper: Mapper<FeedbackModel, FeedbackEntity> {
    override fun mapFrom(from: FeedbackModel): FeedbackEntity
            = FeedbackEntity(
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

    fun mapEntityToModel(from: FeedbackEntity): FeedbackModel
            = FeedbackModel(
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
}
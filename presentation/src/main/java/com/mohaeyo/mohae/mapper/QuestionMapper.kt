package com.mohaeyo.mohae.mapper

import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.mapper.Mapper
import com.mohaeyo.mohae.model.QuestionModel

class QuestionMapper: Mapper<QuestionModel, QuestionEntity> {
    override fun mapFrom(from: QuestionModel): QuestionEntity
            = QuestionEntity(
        id = from.id,
        username = from.username,
        title = from.title,
        address = from.address,
        imageFile = from.imageFile,
        summary = from.summary,
        description = from.description
    )

    fun mapEntityToModel(from: QuestionEntity): QuestionModel
            = QuestionModel(
        id = from.id,
        username = from.username,
        title = from.title,
        address = from.address,
        imageFile = from.imageFile,
        summary = from.summary,
        description = from.description
    )
}
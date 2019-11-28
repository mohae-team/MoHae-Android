package com.mohaeyo.mohae.mapper

import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.mapper.Mapper
import com.mohaeyo.mohae.model.AnswerModel

class AnswerMapper: Mapper<AnswerModel, AnswerEntity> {
    override fun mapFrom(from: AnswerModel): AnswerEntity
            = AnswerEntity(
        id = from.id,
        questionId = from.questionId,
        username = from.username,
        answer = from.answer
    )

    fun mapEntityToModel(from: AnswerEntity): AnswerModel
            = AnswerModel(
        id = from.id,
        questionId = from.questionId,
        username = from.username,
        answer = from.answer
    )
}
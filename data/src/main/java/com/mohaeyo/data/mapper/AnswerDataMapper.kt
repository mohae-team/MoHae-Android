package com.mohaeyo.data.mapper

import com.mohaeyo.data.entity.AnswerData
import com.mohaeyo.data.local.database.entity.Answer
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.mapper.Mapper

class AnswerDataMapper: Mapper<AnswerEntity, AnswerData> {
    override fun mapFrom(from: AnswerEntity): AnswerData
            = AnswerData(
        id = from.id,
        questionId = from.questionId,
        username = from.username,
        answer = from.answer
    )

    fun mapDataToEntity(from: AnswerData): AnswerEntity
            = AnswerEntity(
        id = from.id,
        questionId = from.questionId,
        username = from.username,
        answer = from.answer
    )

    fun mapDbToEntity(from: Answer): AnswerEntity
            = AnswerEntity(
        id = from.id,
        questionId = from.questionId,
        username = from.username,
        answer = from.answer
    )

    fun mapEntityToDb(from: AnswerEntity): Answer
            = Answer(
        id = from.id,
        questionId = from.questionId,
        username = from.username,
        answer = from.answer
    )
}
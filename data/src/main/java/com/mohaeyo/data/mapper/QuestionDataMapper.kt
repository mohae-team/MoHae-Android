package com.mohaeyo.data.mapper

import com.mohaeyo.data.dto.QuestionDto
import com.mohaeyo.data.entity.QuestionData
import com.mohaeyo.data.local.database.entity.Question
import com.mohaeyo.data.toFile
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.mapper.Mapper

class QuestionDataMapper: Mapper<QuestionEntity, QuestionData> {
    override fun mapFrom(from: QuestionEntity): QuestionData
            = QuestionData(
        id = from.id,
        username = from.username,
        title = from.title,
        address = from.address,
        imageFile = from.imageFile,
        summary = from.summary,
        description = from.description
    )

    fun mapDataToEntity(from: QuestionData): QuestionEntity
            = QuestionEntity(
        id = from.id,
        username = from.username,
        title = from.title,
        address = from.address,
        imageFile = from.imageFile,
        summary = from.summary,
        description = from.description
    )

    fun mapDbToEntity(from: Question): QuestionEntity
            = QuestionEntity(
        id = from.id,
        username = from.username,
        title = from.title,
        address = from.address,
        imageFile = from.imageUri.toFile(),
        summary = from.summary,
        description = from.description
    )

    fun mapEntityToDb(from: QuestionEntity): Question
            = Question(
        id = from.id,
        username = from.username,
        title = from.title,
        address = from.address,
        imageUri = from.imageFile.toString(),
        summary = from.summary,
        description = from.description
    )

    fun mapEntityToDto(from: QuestionEntity): QuestionDto
            = QuestionDto(
        id = from.id,
        username = from.username,
        title = from.title,
        address = from.address,
        imageUri = from.imageFile.toString(),
        summary = from.summary,
        description = from.description
    )

    fun mapDtoToEntity(from: QuestionDto): QuestionEntity
            = QuestionEntity(
        id = from.id,
        username = from.username,
        title = from.title,
        address = from.address,
        imageFile = from.imageUri.toFile(),
        summary = from.summary,
        description = from.description
    )
}
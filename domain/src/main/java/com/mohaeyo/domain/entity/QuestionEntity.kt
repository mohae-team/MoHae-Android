package com.mohaeyo.domain.entity

data class QuestionEntity(
    val postId: Int,
    val title: String,
    val writer: String,
    val address: String,
    val summary: String,
    val imageByteList: List<Byte>,
    val description: String,
    val answerList: List<AnswerEntity>
)
package com.mohaeyo.domain.entity

data class AnswerEntity(
    val id: Int,
    val questionId: Int,
    val username: String,
    val answer: String
)
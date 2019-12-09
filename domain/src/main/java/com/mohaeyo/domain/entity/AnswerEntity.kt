package com.mohaeyo.domain.entity

data class AnswerEntity(
    val id: Int = 0,
    val questionId: Int = 0,
    val username: String = "",
    val answer: String = ""
)
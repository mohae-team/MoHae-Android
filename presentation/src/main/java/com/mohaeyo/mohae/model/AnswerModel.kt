package com.mohaeyo.mohae.model

data class AnswerModel(
    val id: Int = 0,
    var questionId: Int = 0,
    var username: String = "",
    var answer: String = ""
)
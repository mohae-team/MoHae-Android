package com.mohaeyo.mohae.model

data class QuestionModel(
    var id: Int,
    val title: String,
    val writer: String,
    val summary: String,
    val imageByteArray: ByteArray,
    val description: String,
    val answers: ArrayList<AnswerModel>
) {
    data class AnswerModel(
        val answer: String,
        val writer: String
    )
}
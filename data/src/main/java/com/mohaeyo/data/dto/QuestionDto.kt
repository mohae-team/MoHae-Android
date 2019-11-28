package com.mohaeyo.data.dto

data class QuestionDto(
    val id: Int,
    val title: String,
    val username: String,
    val address: String,
    val summary: String,
    val imageUri: String,
    val description: String
)
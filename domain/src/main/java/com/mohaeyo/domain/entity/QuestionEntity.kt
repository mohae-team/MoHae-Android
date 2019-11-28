package com.mohaeyo.domain.entity

import java.io.File

data class QuestionEntity(
    val id: Int,
    val title: String,
    val username: String,
    val address: String,
    val summary: String,
    val imageFile: File,
    val description: String
)
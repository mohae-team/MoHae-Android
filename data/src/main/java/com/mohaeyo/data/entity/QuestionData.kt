package com.mohaeyo.data.entity

import java.io.File

data class QuestionData(
    val id: Int,
    val title: String,
    val username: String,
    val address: String,
    val summary: String,
    val imageFile: File,
    val description: String
)
package com.mohaeyo.mohae.model

data class FeedbackModel(
    val id: Int,
    val title: String,
    val address: String,
    val summary: String,
    val imageByteArray: ByteArray,
    val description: String,
    val like: String,
    val hate: String,
    val isLike: Boolean
)
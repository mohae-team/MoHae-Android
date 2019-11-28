package com.mohaeyo.domain.entity

import java.io.File

data class FeedbackEntity(
    val id: Int,
    val placeName: String,
    val location: String,
    val address: String,
    val summary: String,
    val imageFile: File,
    val description: String,
    val likeCount: Int,
    val hateCount: Int,
    val isHate: Boolean,
    val isLike: Boolean
)
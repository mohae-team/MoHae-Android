package com.mohaeyo.domain.entity

import java.io.File

data class FeedbackEntity(
    val id: Int = 0,
    val placeName: String = "",
    val location: String = "",
    val address: String = "",
    val summary: String = "",
    val imageFile: File = File(""),
    val description: String = "",
    val likeCount: Int = 0,
    val hateCount: Int = 0,
    val isHate: Boolean = false,
    val isLike: Boolean = false
)
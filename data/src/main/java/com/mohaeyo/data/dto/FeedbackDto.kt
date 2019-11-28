package com.mohaeyo.data.dto

data class FeedbackDto(
    val id: Int,
    val placeName: String,
    val location: String,
    val address: String,
    val summary: String,
    val imageUri: String,
    val description: String,
    val likeCount: Int,
    val hateCount: Int,
    val isLike: Boolean,
    val isHate: Boolean
)
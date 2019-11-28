package com.mohaeyo.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Feedback(
    @PrimaryKey
    val id: Int,
    val placeName: String,
    val location: String,
    val address: String,
    val summary: String,
    val imageUri: String,
    val description: String,
    val likeCount: Int,
    val hateCount: Int,
    val isHate: Boolean,
    val isLike: Boolean
)
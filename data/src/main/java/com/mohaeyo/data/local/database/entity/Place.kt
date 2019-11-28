package com.mohaeyo.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey
    val location: String,
    val placeName: String,
    val description: String,
    val likeCount: Int,
    val isLike: Boolean
)
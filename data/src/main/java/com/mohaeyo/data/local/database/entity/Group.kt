package com.mohaeyo.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group (
    @PrimaryKey
    val id: Int,
    val title: String,
    val location: String,
    val address: String,
    val term: String,
    val summary: String,
    val imageUri: String,
    val description: String,
    val maxCount: Int,
    val count: Int,
    val isJoin: Boolean
)
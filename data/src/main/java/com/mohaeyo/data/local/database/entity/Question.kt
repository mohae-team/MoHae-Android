package com.mohaeyo.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey
    val id: Int,
    val title: String,
    val username: String,
    val address: String,
    val summary: String,
    val imageUri: String,
    val description: String
)
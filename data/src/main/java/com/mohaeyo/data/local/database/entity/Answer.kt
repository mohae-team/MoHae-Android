package com.mohaeyo.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Answer(
    @PrimaryKey
    val id: Int,
    val questionId: Int,
    val username: String,
    val answer: String
)
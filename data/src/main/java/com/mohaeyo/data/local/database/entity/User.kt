package com.mohaeyo.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: String,
    val password: String,
    val username: String,
    val imageUri: String,
    val address: String,
    val description: String
)
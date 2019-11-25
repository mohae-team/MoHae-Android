package com.mohaeyo.data.dto

data class GroupDto (
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
    val join: Boolean
)
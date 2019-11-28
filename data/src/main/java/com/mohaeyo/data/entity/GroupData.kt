package com.mohaeyo.data.entity

import java.io.File

data class GroupData(
    val id: Int,
    val title: String,
    val location: String,
    val address: String,
    val term: String,
    val summary: String,
    val imageFile: File,
    val description: String,
    val maxCount: Int,
    val count: Int,
    val isJoin: Boolean
)
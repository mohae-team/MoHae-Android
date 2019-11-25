package com.mohaeyo.domain.entity

import java.io.File

data class GroupEntity(
    val id: Int = 0,
    val title: String =  "",
    val location: String = "",
    val address: String = "",
    val term: String = "",
    val summary: String = "",
    val imageFile: File = File(""),
    val description: String = "",
    val maxCount: Int = 0,
    val count: Int = 0,
    val isJoin: Boolean = false
)
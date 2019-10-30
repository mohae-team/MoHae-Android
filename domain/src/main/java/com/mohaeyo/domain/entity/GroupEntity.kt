package com.mohaeyo.domain.entity

data class GroupEntity(
    val id: Int,
    val title: String,
    val location: String,
    val address: String,
    val term: String,
    val summary: String,
    val imageByteList: List<Byte>,
    val description: String,
    val maxCount: Int,
    val peopleId: List<String>
)
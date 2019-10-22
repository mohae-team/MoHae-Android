package com.mohaeyo.mohae.model

data class GroupModel(
    val id: Int,
    val title: String,
    val address: String,
    val term: String,
    val summary: String,
    val imageUrl: ByteArray,
    val description: String,
    val count: String,
    val isJoin: Boolean
)
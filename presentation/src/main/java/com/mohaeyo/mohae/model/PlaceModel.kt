package com.mohaeyo.mohae.model

data class PlaceModel(
    val name: String = "",
    val location: String = "",
    val likeCount: Int = 0,
    val description: String = "",
    val isLike: Boolean
)
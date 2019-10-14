package com.mohaeyo.mohae.model

data class PlaceModel(
    val name: String,
    var likeCount: Int,
    val description: String,
    val location: String,
    var isLike: Boolean
)
package com.mohaeyo.mohae.model

data class PlaceModel(
    var name: String = "",
    var location: String = "",
    var likeCount: Int = 0,
    var description: String = "",
    var isLike: Boolean = false
)
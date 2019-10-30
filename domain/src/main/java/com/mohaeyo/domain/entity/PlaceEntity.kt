package com.mohaeyo.domain.entity

data class PlaceEntity(
    val placeName: String,
    val location: String,
    val description: String,
    val likePeople: List<String>
)
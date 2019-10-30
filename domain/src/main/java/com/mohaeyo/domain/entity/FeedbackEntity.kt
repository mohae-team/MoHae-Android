package com.mohaeyo.domain.entity

data class FeedbackEntity(
    val id: Int,
    val placeName: String,
    val location: String,
    val address: String,
    val summary: String,
    val imageByteList: List<Byte>,
    val description: String,
    val likePeopleId: List<String>,
    val hatePeopleId: List<String>
)
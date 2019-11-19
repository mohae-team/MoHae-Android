package com.mohaeyo.data.entity

import com.google.gson.annotations.SerializedName

data class PlaceData(
    val placeName: String = "",
    val location: String = "",
    val description: String = "",
    val likeCount: Int = 0,
    @SerializedName("like")
    val isLike: Boolean = false
)
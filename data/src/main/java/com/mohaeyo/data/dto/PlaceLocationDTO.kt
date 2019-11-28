package com.mohaeyo.data.dto

import com.google.gson.annotations.SerializedName

data class PlaceLocationDTO(
    @SerializedName("location")
    val location: String
)
package com.mohaeyo.data.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("imageUri")
    val imageUri: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String
)
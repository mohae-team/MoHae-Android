package com.mohaeyo.data.entity

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("imageByteList")
    val imageByteList: List<Byte>,
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String
)
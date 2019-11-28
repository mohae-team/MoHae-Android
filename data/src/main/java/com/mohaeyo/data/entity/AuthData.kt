package com.mohaeyo.data.entity

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String
)
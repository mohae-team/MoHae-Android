package com.mohaeyo.data.entity

import com.google.gson.annotations.SerializedName

data class TokenData(
    @SerializedName(value = "token")
    val token: String
)
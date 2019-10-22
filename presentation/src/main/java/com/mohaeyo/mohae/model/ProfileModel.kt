package com.mohaeyo.mohae.model

data class ProfileModel(
    val imageByteArray: ByteArray,
    val name: String,
    val address: String,
    val id: String,
    val description: String
)
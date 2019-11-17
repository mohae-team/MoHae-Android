package com.mohaeyo.data.entity


import java.io.File

data class UserData(
    val id: String,

    val password: String,

    val username: String,

    val imageFile: File,

    val address: String,

    val description: String
)
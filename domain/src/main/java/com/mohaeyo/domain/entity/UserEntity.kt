package com.mohaeyo.domain.entity

import java.io.File

data class UserEntity(
    val id: String = "",
    val password: String = "",
    val username: String = "",
    val imageFile: File = File(""),
    val address: String = "",
    val description: String = ""
)
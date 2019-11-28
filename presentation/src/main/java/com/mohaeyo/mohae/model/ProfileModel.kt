package com.mohaeyo.mohae.model

import java.io.File

data class ProfileModel(
    val imageFile: File = File(""),
    val name: String = "",
    val address: String = "",
    val id: String = "",
    val description: String = ""
)
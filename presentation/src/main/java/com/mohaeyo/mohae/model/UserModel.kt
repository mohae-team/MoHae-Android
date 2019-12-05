package com.mohaeyo.mohae.model

import java.io.File

data class UserModel(
    val id: String = "",
    var password: String = "",
    var username: String = "",
    var imageFile: File = File(""),
    var address: String = "",
    var description: String = ""
)
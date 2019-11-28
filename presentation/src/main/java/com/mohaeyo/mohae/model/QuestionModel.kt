package com.mohaeyo.mohae.model

import java.io.File

data class QuestionModel(
    val id: Int = 0,
    var title: String = "",
    var username: String = "",
    var address: String = "",
    var summary: String = "",
    var imageFile: File = File(""),
    var description: String = ""
)
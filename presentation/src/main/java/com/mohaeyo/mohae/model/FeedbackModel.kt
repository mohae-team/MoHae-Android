package com.mohaeyo.mohae.model

import java.io.File

data class FeedbackModel(
    val id: Int = 0,
    var placeName: String = "",
    var location: String = "",
    var address: String = "",
    var summary: String = "",
    var imageFile: File = File(""),
    var description: String = "",
    var likeCount: Int = 0,
    var hateCount: Int = 0,
    var isHate: Boolean = false,
    var isLike: Boolean = false
)
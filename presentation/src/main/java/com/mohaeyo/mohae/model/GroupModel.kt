package com.mohaeyo.mohae.model

import java.io.File

data class GroupModel(
    val id: Int = 0,
    var title: String = "",
    var location: String = "",
    var address: String = "",
    var term: String = "",
    var summary: String = "",
    var imageFile: File = File(""),
    var description: String = "",
    var count: Int = 0,
    var maxCount: Int = 0,
    var countText: String = "",
    var isJoin: Boolean = false
)
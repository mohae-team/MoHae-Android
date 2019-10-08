package com.mohaeyo.mohae.model

data class FeedbackModel(
    val id: Int,
    val title: String,
    val address: String,
    val summary: String,
    val imageUrl: String,
    val description: String,
    val like: String,
    val hate: String
)
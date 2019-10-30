package com.mohaeyo.domain.entity

data class UserEntity(
    val id: String,
    val password: String,
    val username: String,
    val imageByteList: List<Byte>,
    val address: String,
    val description: String
)
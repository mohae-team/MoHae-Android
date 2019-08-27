package com.mohaeyo.domain.mapper

interface Mapper<in T, E>{
    fun mapFrom(from: T): E
}
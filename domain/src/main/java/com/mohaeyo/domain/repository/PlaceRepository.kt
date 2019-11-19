package com.mohaeyo.domain.repository

import com.mohaeyo.domain.entity.PlaceEntity
import io.reactivex.Flowable

interface PlaceRepository {
    fun getRemotePlace(location: String): Flowable<PlaceEntity>

    fun postRemotePlace(place: PlaceEntity): Flowable<PlaceEntity>

    fun getLocalPlace(): PlaceEntity

    fun saveLocalPlace(place: PlaceEntity)

    fun postLikeRemotePlace(location: String): Flowable<PlaceEntity>

    fun postDisLikeRemotePlace(location: String): Flowable<PlaceEntity>
}
package com.mohaeyo.data.datasource

import com.mohaeyo.data.entity.PlaceData
import com.mohaeyo.data.local.database.entity.Place
import io.reactivex.Flowable

interface PlaceDataSource {
    fun getRemotePlace(location: String): Flowable<PlaceData>

    fun postRemotePlace(place: PlaceData): Flowable<PlaceData>

    fun getLocalPlace(): Place

    fun saveLocalPlace(place: Place)

    fun postRemoteLikePlace(location: String): Flowable<PlaceData>

    fun postRemoteDisLikePlace(location: String): Flowable<PlaceData>
}
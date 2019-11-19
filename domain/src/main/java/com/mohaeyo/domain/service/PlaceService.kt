package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.PlaceEntity
import io.reactivex.Flowable

interface PlaceService {
    fun getPlaceInfo(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>

    fun postPlaceInfo(placeEntity: PlaceEntity): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>

    fun postLikePlace(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>

    fun postDisLikePlace(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
}
package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.PlaceLocationDTO
import com.mohaeyo.data.entity.PlaceData
import com.mohaeyo.data.local.database.dao.PlaceDao
import com.mohaeyo.data.local.database.entity.Place
import com.mohaeyo.data.remote.Api
import io.reactivex.Flowable

class PlaceDataSourceImpl(private val api: Api,
                          private val placeDao: PlaceDao): PlaceDataSource {
    override fun getRemotePlace(location: String): Flowable<PlaceData>
            = api.getPlace(location)

    override fun postRemotePlace(place: PlaceData): Flowable<PlaceData>
            = api.postPlace(place)

    override fun getLocalPlace(): Place
            = placeDao.getPlace()

    override fun saveLocalPlace(place: Place)
            = placeDao.savePlace(place)

    override fun postRemoteLikePlace(location: String): Flowable<PlaceData>
            = api.postLikePlace(PlaceLocationDTO(location))

    override fun postRemoteDisLikePlace(location: String): Flowable<PlaceData>
            = api.postDisLikePlace(PlaceLocationDTO(location))
}
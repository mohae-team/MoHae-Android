package com.mohaeyo.data.repository

import com.mohaeyo.data.datasource.PlaceDataSource
import com.mohaeyo.data.entity.PlaceData
import com.mohaeyo.data.local.database.entity.Place
import com.mohaeyo.data.mapper.PlaceDataMapper
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.repository.PlaceRepository
import io.reactivex.Flowable

class PlaceRepositoryImpl(private val datasource: PlaceDataSource,
                          private val placeDataMapper: PlaceDataMapper): PlaceRepository {

    override fun getRemotePlace(location: String): Flowable<PlaceEntity>
            = datasource.getRemotePlace(location).map { placeDataMapper.mapDataToEntity(it) }

    override fun postRemotePlace(place: PlaceEntity): Flowable<PlaceEntity>
            = datasource.postRemotePlace(placeDataMapper.mapFrom(place)).map { placeDataMapper.mapDataToEntity(it) }

    override fun getLocalPlace(): PlaceEntity
            = placeDataMapper.mapDbToEntity(datasource.getLocalPlace())

    override fun saveLocalPlace(place: PlaceEntity)
            = datasource.saveLocalPlace(placeDataMapper.mapEntityToDb(place))

    override fun postLikeRemotePlace(location: String): Flowable<PlaceEntity>
            = datasource.postRemoteLikePlace(location).map { placeDataMapper.mapDataToEntity(it) }

    override fun postDisLikeRemotePlace(location: String): Flowable<PlaceEntity>
            = datasource.postRemoteDisLikePlace(location).map { placeDataMapper.mapDataToEntity(it) }
}
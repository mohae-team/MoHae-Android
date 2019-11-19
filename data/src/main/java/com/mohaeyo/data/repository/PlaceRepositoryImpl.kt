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
    val mapPlaceEntityToData: (PlaceEntity) -> PlaceData
            = { placeDataMapper.mapFrom(it) }

    val mapPlaceDataToEntity: (PlaceData) -> PlaceEntity
            = { placeDataMapper.mapDtoToEntity(it) }

    val mapPlaceDbToEntity: (Place) -> PlaceEntity
            = { placeDataMapper.mapDbToEntity(it) }

    val mapPlaceEntityToDb: (PlaceEntity) -> Place
            = { placeDataMapper.mapEntityToDb(it) }

    override fun getRemotePlace(location: String): Flowable<PlaceEntity>
            = datasource.getRemotePlace(location).map { mapPlaceDataToEntity(it) }

    override fun postRemotePlace(place: PlaceEntity): Flowable<PlaceEntity>
            = datasource.postRemotePlace(mapPlaceEntityToData(place)).map { mapPlaceDataToEntity(it) }

    override fun getLocalPlace(): PlaceEntity
            = mapPlaceDbToEntity(datasource.getLocalPlace())

    override fun saveLocalPlace(place: PlaceEntity)
            = datasource.saveLocalPlace(mapPlaceEntityToDb(place))

    override fun postLikeRemotePlace(location: String): Flowable<PlaceEntity>
            = datasource.postRemoteLikePlace(location).map { mapPlaceDataToEntity(it) }

    override fun postDisLikeRemotePlace(location: String): Flowable<PlaceEntity>
            = datasource.postRemoteDisLikePlace(location).map { mapPlaceDataToEntity(it) }
}
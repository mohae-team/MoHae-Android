package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.handler.PlaceErrorHandler
import com.mohaeyo.domain.repository.PlaceRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class PlaceServiceImpl(
    private val placeRepository: PlaceRepository,
    private val placeErrorHandler: PlaceErrorHandler): PlaceService{
    override fun getPlaceInfo(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.getRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        placeRepository.saveLocalPlace(it.first)
    }.onErrorReturn {
        placeRepository.getLocalPlace() to placeErrorHandler.getInfoErrorHandle(it)
    }

    override fun postPlaceInfo(placeEntity: PlaceEntity): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postRemotePlace(placeEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        placeEntity to placeErrorHandler.postInfoErrorHandle(it)
    }

    override fun postLikePlace(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postLikeRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        placeRepository.getLocalPlace() to placeErrorHandler.postLikeErrorHandle(it)
    }

    override fun postDisLikePlace(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postDisLikeRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        placeRepository.getLocalPlace() to placeErrorHandler.postDisLikeErrorHandle(it)
    }
}
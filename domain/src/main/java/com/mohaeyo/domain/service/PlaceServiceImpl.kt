package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.repository.PlaceRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class PlaceServiceImpl(private val placeRepository: PlaceRepository): PlaceService{
    override fun getPlaceInfo(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.getRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        placeRepository.saveLocalPlace(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            placeRepository.getLocalPlace() to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "아직 등록되지 않은 장소입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else placeRepository.getLocalPlace() to ErrorHandlerEntity(isSuccess = true)
    }

    override fun postPlaceInfo(placeEntity: PlaceEntity): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postRemotePlace(placeEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            placeRepository.getLocalPlace() to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 유저정보입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else placeRepository.getLocalPlace() to ErrorHandlerEntity(isSuccess = true)
    }

    override fun postLikePlace(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postLikeRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            placeRepository.getLocalPlace() to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "아직 등록되지 않은 장소입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else placeRepository.getLocalPlace() to ErrorHandlerEntity(isSuccess = true)
    }

    override fun postDisLikePlace(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postDisLikeRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            placeRepository.getLocalPlace() to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "아직 등록되지 않은 장소입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else placeRepository.getLocalPlace() to ErrorHandlerEntity(isSuccess = true)
    }
}
package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.repository.PlaceRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class PlaceServiceImpl(private val placeRepository: PlaceRepository): PlaceService{
    override fun getPlaceInfo(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.getRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        placeRepository.saveLocalPlace(it.first)
    }.onErrorReturn {
        placeRepository.getLocalPlace() to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "아직 등록되지 않은 장소입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun postPlaceInfo(placeEntity: PlaceEntity): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postRemotePlace(placeEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        placeRepository.getLocalPlace() to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 유저정보입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun postLikePlace(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postLikeRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        placeRepository.getLocalPlace() to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "아직 등록되지 않은 장소입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                409 -> ErrorHandlerEntity(message = "이미 좋아요가 반영되었습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun postDisLikePlace(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeRepository.postDisLikeRemotePlace(location).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        placeRepository.getLocalPlace() to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "아직 등록되지 않은 장소입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                409 -> ErrorHandlerEntity(message = "이미 좋아요가 해제되었습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }
}
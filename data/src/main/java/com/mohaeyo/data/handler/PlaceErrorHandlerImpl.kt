package com.mohaeyo.data.handler

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.handler.PlaceErrorHandler
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class PlaceErrorHandlerImpl: PlaceErrorHandler {
    override fun getInfoErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when(throwable.code()) {
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

    override fun postInfoErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when(throwable.code()) {
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

    override fun postLikeErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when(throwable.code()) {
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

    override fun postDisLikeErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when(throwable.code()) {
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
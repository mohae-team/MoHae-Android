package com.mohaeyo.data.handler

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.handler.GroupErrorHandler
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class GroupErrorHandlerImpl: GroupErrorHandler {
    override fun getListErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when(throwable.code()) {
            200 -> ErrorHandlerEntity(isSuccess = true)
            404 -> ErrorHandlerEntity(message = "아직 모임이 없습니다")
            403 -> ErrorHandlerEntity(message = "권한이 없습니다")
            500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
            else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
        }
        is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
        is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
        else -> ErrorHandlerEntity()
    }

    override fun getDetailErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when(throwable.code()) {
            200 -> ErrorHandlerEntity(isSuccess = true)
            404 -> ErrorHandlerEntity(message = "존재하지 않는 모임입니다")
            403 -> ErrorHandlerEntity(message = "권한이 없습니다")
            500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
            else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
        }
        is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
        is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
        else -> ErrorHandlerEntity()
    }

    override fun createErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when(throwable.code()) {
            200 -> ErrorHandlerEntity(isSuccess = true)
            404 -> ErrorHandlerEntity(message = "존재하지 않는 유저입니다")
            403 -> ErrorHandlerEntity(message = "권한이 없습니다")
            500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
            else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
        }
        is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
        is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
        else -> ErrorHandlerEntity()
    }

    override fun cancelErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when(throwable.code()) {
            200 -> ErrorHandlerEntity(isSuccess = true)
            404 -> ErrorHandlerEntity(message = "존재하지 않는 모임입니다")
            403 -> ErrorHandlerEntity(message = "권한이 없습니다")
            500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
            else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
        }
        is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
        is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
        else -> ErrorHandlerEntity()
    }

    override fun joinErrorHandle(throwable: Throwable): ErrorHandlerEntity
            = when (throwable) {
        is HttpException -> when (throwable.code()) {
            200 -> ErrorHandlerEntity(isSuccess = true)
            404 -> ErrorHandlerEntity(message = "존재하지 않는 모임입니다")
            403 -> ErrorHandlerEntity(message = "권한이 없습니다")
            500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
            else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
        }
        is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
        is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
        else -> ErrorHandlerEntity()
    }
}
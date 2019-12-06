package com.mohaeyo.domain.handler

import com.mohaeyo.domain.base.ErrorHandlerEntity

interface PlaceErrorHandler {
    fun getInfoErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun postInfoErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun postLikeErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun postDisLikeErrorHandle(throwable: Throwable): ErrorHandlerEntity
}
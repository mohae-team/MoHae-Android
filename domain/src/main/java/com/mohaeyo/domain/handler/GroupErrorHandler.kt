package com.mohaeyo.domain.handler

import com.mohaeyo.domain.base.ErrorHandlerEntity

interface GroupErrorHandler {
    fun getListErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun getDetailErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun createErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun cancelErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun joinErrorHandle(throwable: Throwable): ErrorHandlerEntity
}
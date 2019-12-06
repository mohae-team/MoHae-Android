package com.mohaeyo.domain.handler

import com.mohaeyo.domain.base.ErrorHandlerEntity

interface UserErrorHandler {
    fun getInfoErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun editInfoErrorHandle(throwable: Throwable): ErrorHandlerEntity
}
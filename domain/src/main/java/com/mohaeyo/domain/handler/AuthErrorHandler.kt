package com.mohaeyo.domain.handler

import com.mohaeyo.domain.base.ErrorHandlerEntity

interface AuthErrorHandler {
    fun signUpErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun signInErrorHandle(throwable: Throwable): ErrorHandlerEntity
}
package com.mohaeyo.domain.handler

import com.mohaeyo.domain.base.ErrorHandlerEntity

interface FeedbackErrorHandler {
    fun getListErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun getDetailErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun createErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun hateErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun likeErrorHandle(throwable: Throwable): ErrorHandlerEntity
}
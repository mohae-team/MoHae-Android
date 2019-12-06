package com.mohaeyo.domain.handler

import com.mohaeyo.domain.base.ErrorHandlerEntity

interface QAErrorHandler {
    fun getQuestionListErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun getQuestionDetailErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun createQuestionErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun getAnswerListErrorHandle(throwable: Throwable): ErrorHandlerEntity

    fun createAnswerErrorHandle(throwable: Throwable): ErrorHandlerEntity
}
package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.FeedbackEntity
import io.reactivex.Flowable

interface FeedbackService {
    fun getListFeedback(): Flowable<Pair<List<FeedbackEntity>, ErrorHandlerEntity>>

    fun getFeedbackDetail(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>

    fun createFeedback(feedback: FeedbackEntity): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>

    fun hateFeedback(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>

    fun likeFeedback(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
}
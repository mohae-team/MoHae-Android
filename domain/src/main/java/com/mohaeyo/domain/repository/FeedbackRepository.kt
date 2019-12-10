package com.mohaeyo.domain.repository

import com.mohaeyo.domain.entity.FeedbackEntity
import io.reactivex.Flowable

interface FeedbackRepository {
    fun getRemoteFeedbackList(): Flowable<List<FeedbackEntity>>

    fun getRemoteFeedbackDetail(id: Int): Flowable<FeedbackEntity>

    fun getLocalFeedbackList(): List<FeedbackEntity>

    fun getLocalFeedbackDetail(id: Int): FeedbackEntity

    fun saveLocalFeedback(feedback: FeedbackEntity)

    fun saveLocalFeedbackList(feedbackList: List<FeedbackEntity>)

    fun createFeedback(feedback: FeedbackEntity): Flowable<FeedbackEntity>

    fun hateFeedback(id: Int): Flowable<FeedbackEntity>

    fun likeFeedback(id: Int): Flowable<FeedbackEntity>
}
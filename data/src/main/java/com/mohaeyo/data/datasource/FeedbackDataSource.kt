package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.FeedbackDto
import com.mohaeyo.data.entity.FeedbackData
import com.mohaeyo.data.local.database.entity.Feedback
import io.reactivex.Flowable

interface FeedbackDataSource {
    fun getRemoteListFeedback(): Flowable<List<FeedbackDto>>

    fun getRemoteFeedbackDetail(id: Int): Flowable<FeedbackDto>

    fun getLocalListFeedback(): List<Feedback>

    fun getLocalFeedbackDetail(id: Int): Feedback

    fun saveLocalFeedback(feedback: Feedback)

    fun saveLocalFeedbackList(feedbackList: List<Feedback>)

    fun postCreateFeedback(feedback: FeedbackData): Flowable<FeedbackDto>

    fun postHateFeedback(id: Int): Flowable<FeedbackDto>

    fun postLikeFeedback(id: Int): Flowable<FeedbackDto>
}
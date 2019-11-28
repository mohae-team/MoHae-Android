package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.FeedbackDto
import com.mohaeyo.data.entity.FeedbackData
import com.mohaeyo.data.local.database.dao.FeedbackDao
import com.mohaeyo.data.local.database.entity.Feedback
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.toImageRequestBody
import io.reactivex.Flowable
import okhttp3.MultipartBody

class FeedbackDataSourceImpl(
    private val api: Api,
    private val feedbackDao: FeedbackDao
): FeedbackDataSource {
    override fun getRemoteListFeedback(): Flowable<List<FeedbackDto>>
            = api.getFeedbackList()

    override fun getRemoteFeedbackDetail(id: Int): Flowable<FeedbackDto>
            = api.getFeedbackDetail(id)

    override fun getLocalListFeedback(): List<Feedback>
            = feedbackDao.getFeedbackList()

    override fun getLocalFeedbackDetail(id: Int): Feedback
            = feedbackDao.getFeedback(id)

    override fun saveLocalFeedback(feedback: Feedback)
            = feedbackDao.saveFeedback(feedback)

    override fun saveLocalFeedbackList(feedbackList: List<Feedback>)
            = feedbackDao.saveFeedback(*feedbackList.toTypedArray())

    override fun postCreateFeedback(feedback: FeedbackData): Flowable<FeedbackDto>
            = api.postCreateFeedback(
        imageFile = MultipartBody.Part.createFormData("imageFile", feedback.imageFile.name, feedback.imageFile.toImageRequestBody()),
        description = MultipartBody.Part.createFormData("description", feedback.description),
        address = MultipartBody.Part.createFormData("address", feedback.address),
        summary = MultipartBody.Part.createFormData("summary", feedback.summary),
        location = MultipartBody.Part.createFormData("location", feedback.location)
    )

    override fun postHateFeedback(id: Int): Flowable<FeedbackDto>
            = api.postFeedbackHate(id)

    override fun postLikeFeedback(id: Int): Flowable<FeedbackDto>
            = api.postFeedbackLike(id)
}
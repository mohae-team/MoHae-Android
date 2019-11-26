package com.mohaeyo.data.repository

import com.mohaeyo.data.datasource.FeedbackDataSource
import com.mohaeyo.data.mapper.FeedbackDataMapper
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.repository.FeedbackRepository
import io.reactivex.Flowable

class FeedbackRepositoryImpl(
    private val datasource: FeedbackDataSource,
    private val feedbackDataMapper: FeedbackDataMapper
): FeedbackRepository {
    override fun getRemoteListGroup(): Flowable<List<FeedbackEntity>>
            = datasource.getRemoteListFeedback().map { list -> list.map { feedbackDataMapper.mapDtoToEntity(it)} }

    override fun getRemoteFeedbackDetail(id: Int): Flowable<FeedbackEntity>
            = datasource.getRemoteFeedbackDetail(id).map { feedbackDataMapper.mapDtoToEntity(it) }

    override fun getLocalListFeedback(): List<FeedbackEntity>
            = datasource.getLocalListFeedback().map { feedbackDataMapper.mapDbToEntity(it) }

    override fun getLocalFeedbackDetail(id: Int): FeedbackEntity
            = feedbackDataMapper.mapDbToEntity(datasource.getLocalFeedbackDetail(id))

    override fun saveLocalFeedback(feedback: FeedbackEntity)
            = datasource.saveLocalFeedback(feedbackDataMapper.mapEntityToDb(feedback))

    override fun saveLocalFeedbackList(feedbackList: List<FeedbackEntity>)
            = datasource.saveLocalFeedbackList(feedbackList.map { feedbackDataMapper.mapEntityToDb(it) })

    override fun createFeedback(feedback: FeedbackEntity): Flowable<FeedbackEntity>
            = datasource.createFeedback(feedbackDataMapper.mapFrom(feedback)).map { feedbackDataMapper.mapDtoToEntity(it) }

    override fun hateFeedback(id: Int): Flowable<FeedbackEntity>
            = datasource.hateFeedback(id).map { feedbackDataMapper.mapDtoToEntity(it) }

    override fun likeFeedback(id: Int): Flowable<FeedbackEntity>
            = datasource.likeFeedback(id).map { feedbackDataMapper.mapDtoToEntity(it) }
}
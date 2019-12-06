package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.handler.FeedbackErrorHandler
import com.mohaeyo.domain.repository.FeedbackRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class FeedbackServiceImpl(
    private val feedbackRepository: FeedbackRepository,
    private val feedbackErrorHandler: FeedbackErrorHandler
): FeedbackService {
    override fun getListFeedback(): Flowable<Pair<List<FeedbackEntity>, ErrorHandlerEntity>>
            = feedbackRepository.getRemoteGroupList().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        feedbackRepository.saveLocalFeedbackList(it.first)
    }.onErrorReturn {
        feedbackRepository.getLocalFeedbackList() to feedbackErrorHandler.getListErrorHandle(it)
    }

    override fun getFeedbackDetail(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.getRemoteFeedbackDetail(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        feedbackRepository.saveLocalFeedback(it.first)
    }.onErrorReturn {
        feedbackRepository.getLocalFeedbackDetail(id) to feedbackErrorHandler.getDetailErrorHandle(it)
    }

    override fun createFeedback(feedback: FeedbackEntity): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.createFeedback(feedback).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        feedback to feedbackErrorHandler.createErrorHandle(it)
    }

    override fun hateFeedback(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.hateFeedback(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        feedbackRepository.getLocalFeedbackDetail(id) to feedbackErrorHandler.hateErrorHandle(it)
    }

    override fun likeFeedback(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.likeFeedback(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        feedbackRepository.getLocalFeedbackDetail(id) to feedbackErrorHandler.likeErrorHandle(it)
    }
}
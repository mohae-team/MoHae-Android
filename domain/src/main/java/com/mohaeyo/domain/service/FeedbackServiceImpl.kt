package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.repository.FeedbackRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class FeedbackServiceImpl(
    private val feedbackRepository: FeedbackRepository
): FeedbackService {
    override fun getListFeedback(): Flowable<Pair<List<FeedbackEntity>, ErrorHandlerEntity>>
            = feedbackRepository.getRemoteGroupList().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        feedbackRepository.saveLocalFeedbackList(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            feedbackRepository.getLocalFeedbackList() to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "아직 게시물이 없습니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else feedbackRepository.getLocalFeedbackList() to ErrorHandlerEntity(isSuccess = true)
    }

    override fun getFeedbackDetail(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.getRemoteFeedbackDetail(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        feedbackRepository.saveLocalFeedback(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            feedbackRepository.getLocalFeedbackDetail(id) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 게시물입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else feedbackRepository.getLocalFeedbackDetail(id) to ErrorHandlerEntity(isSuccess = true)
    }

    override fun createFeedback(feedback: FeedbackEntity): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.createFeedback(feedback).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            feedback to ErrorHandlerEntity(message =
            when(it.code()) {
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else feedback to ErrorHandlerEntity(isSuccess = true)
    }

    override fun hateFeedback(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.hateFeedback(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            feedbackRepository.getLocalFeedbackDetail(id) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 게시물입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else feedbackRepository.getLocalFeedbackDetail(id) to ErrorHandlerEntity(isSuccess = true)
    }

    override fun likeFeedback(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.likeFeedback(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            feedbackRepository.getLocalFeedbackDetail(id) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 게시물입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else feedbackRepository.getLocalFeedbackDetail(id) to ErrorHandlerEntity(isSuccess = true)
    }
}
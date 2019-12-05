package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.repository.FeedbackRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class FeedbackServiceImpl(
    private val feedbackRepository: FeedbackRepository
): FeedbackService {
    override fun getListFeedback(): Flowable<Pair<List<FeedbackEntity>, ErrorHandlerEntity>>
            = feedbackRepository.getRemoteGroupList().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        feedbackRepository.saveLocalFeedbackList(it.first)
    }.onErrorReturn {
        feedbackRepository.getLocalFeedbackList() to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "아직 피드백이 없습니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun getFeedbackDetail(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.getRemoteFeedbackDetail(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        feedbackRepository.saveLocalFeedback(it.first)
    }.onErrorReturn {
        feedbackRepository.getLocalFeedbackDetail(id) to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 피드백입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun createFeedback(feedback: FeedbackEntity): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.createFeedback(feedback).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        feedback to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "아직 등록되지 않은 장소입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun hateFeedback(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.hateFeedback(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        feedbackRepository.getLocalFeedbackDetail(id) to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 피드백입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun likeFeedback(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackRepository.likeFeedback(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        feedbackRepository.getLocalFeedbackDetail(id) to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 피드백입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }
}
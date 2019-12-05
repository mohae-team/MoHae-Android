package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.repository.QARepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class QAServiceImpl(private val qaRepository: QARepository): QAService {
    override fun getQuestionList(): Flowable<Pair<List<QuestionEntity>, ErrorHandlerEntity>>
            = qaRepository.getRemoteQuestionList().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalQuestionList(it.first)
    }.onErrorReturn {
        qaRepository.getLocalQuestionList() to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "아직 질문이 없습니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun getQuestionDetail(id: Int): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>
            = qaRepository.getRemoteQuestionDetail(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalQuestion(it.first)
    }.onErrorReturn {
        qaRepository.getLocalQuestionDetail(id) to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 질문입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun createQuestion(questionEntity: QuestionEntity): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>
            = qaRepository.postCreateQuestion(questionEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        questionEntity to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 유저입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun getAnswerList(id: Int): Flowable<Pair<List<AnswerEntity>, ErrorHandlerEntity>>
            = qaRepository.getRemoteAnswerList(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalAnswerList(it.first)
    }.onErrorReturn {
        qaRepository.getLocalAnswerList(id) to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "아직 답변이 없습니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }

    override fun createAnswer(answerEntity: AnswerEntity): Flowable<Pair<AnswerEntity, ErrorHandlerEntity>>
            = qaRepository.postCreateAnswer(answerEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        answerEntity to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 답변이 입니다")
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
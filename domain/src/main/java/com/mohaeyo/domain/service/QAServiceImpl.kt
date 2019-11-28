package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.repository.QARepository
import io.reactivex.Flowable
import retrofit2.HttpException

class QAServiceImpl(private val qaRepository: QARepository): QAService {
    override fun getQuestionList(): Flowable<Pair<List<QuestionEntity>, ErrorHandlerEntity>>
            = qaRepository.getRemoteQuestionList().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalQuestionList(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            qaRepository.getLocalQuestionList() to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "아직 질문이 없습니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else qaRepository.getLocalQuestionList() to ErrorHandlerEntity(isSuccess = true)
    }

    override fun getQuestionDetail(id: Int): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>
            = qaRepository.getRemoteQuestionDetail(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalQuestion(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            qaRepository.getLocalQuestionDetail(id) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 게시물입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else qaRepository.getLocalQuestionDetail(id) to ErrorHandlerEntity(isSuccess = true)
    }

    override fun createQuestion(questionEntity: QuestionEntity): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>
            = qaRepository.postCreateQuestion(questionEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            questionEntity to ErrorHandlerEntity(message =
            when(it.code()) {
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else questionEntity to ErrorHandlerEntity(isSuccess = true)
    }

    override fun getAnswerList(id: Int): Flowable<Pair<List<AnswerEntity>, ErrorHandlerEntity>>
            = qaRepository.getRemoteAnswerList(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalAnswerList(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            qaRepository.getLocalAnswerList(id) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "아직 답변이 없습니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else qaRepository.getLocalAnswerList(id) to ErrorHandlerEntity(isSuccess = true)
    }

    override fun createAnswer(answerEntity: AnswerEntity): Flowable<Pair<AnswerEntity, ErrorHandlerEntity>>
            = qaRepository.postCreateAnswer(answerEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            answerEntity to ErrorHandlerEntity(message =
            when(it.code()) {
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else answerEntity to ErrorHandlerEntity(isSuccess = true)
    }
}
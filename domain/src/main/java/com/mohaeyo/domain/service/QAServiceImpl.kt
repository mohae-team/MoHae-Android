package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.handler.QAErrorHandler
import com.mohaeyo.domain.repository.QARepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class QAServiceImpl(
    private val qaRepository: QARepository,
    private val qaErrorHandler: QAErrorHandler): QAService {
    override fun getQuestionList(): Flowable<Pair<List<QuestionEntity>, ErrorHandlerEntity>>
            = qaRepository.getRemoteQuestionList().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalQuestionList(it.first)
    }.onErrorReturn {
        qaRepository.getLocalQuestionList() to qaErrorHandler.getQuestionListErrorHandle(it)
    }

    override fun getQuestionDetail(id: Int): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>
            = qaRepository.getRemoteQuestionDetail(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalQuestion(it.first)
    }.onErrorReturn {
        qaRepository.getLocalQuestionDetail(id) to qaErrorHandler.getQuestionDetailErrorHandle(it)
    }

    override fun createQuestion(questionEntity: QuestionEntity): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>
            = qaRepository.postCreateQuestion(questionEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        questionEntity to qaErrorHandler.createQuestionErrorHandle(it)
    }

    override fun getAnswerList(id: Int): Flowable<Pair<List<AnswerEntity>, ErrorHandlerEntity>>
            = qaRepository.getRemoteAnswerList(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        qaRepository.saveLocalAnswerList(it.first)
    }.onErrorReturn {
        qaRepository.getLocalAnswerList(id) to qaErrorHandler.getAnswerListErrorHandle(it)
    }

    override fun createAnswer(answerEntity: AnswerEntity): Flowable<Pair<AnswerEntity, ErrorHandlerEntity>>
            = qaRepository.postCreateAnswer(answerEntity).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        answerEntity to qaErrorHandler.createAnswerErrorHandle(it)
    }
}
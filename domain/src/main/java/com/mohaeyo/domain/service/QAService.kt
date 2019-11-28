package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import io.reactivex.Flowable

interface QAService {
    fun getQuestionList(): Flowable<Pair<List<QuestionEntity>, ErrorHandlerEntity>>

    fun getQuestionDetail(id: Int): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>

    fun createQuestion(questionEntity: QuestionEntity): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>

    fun getAnswerList(id: Int): Flowable<Pair<List<AnswerEntity>, ErrorHandlerEntity>>

    fun createAnswer(answerEntity: AnswerEntity): Flowable<Pair<AnswerEntity, ErrorHandlerEntity>>
}
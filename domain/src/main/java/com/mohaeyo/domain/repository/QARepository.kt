package com.mohaeyo.domain.repository

import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import io.reactivex.Flowable

interface QARepository {
    fun getRemoteQuestionList(): Flowable<List<QuestionEntity>>

    fun saveLocalQuestion(question: QuestionEntity)

    fun saveLocalQuestionList(questionList: List<QuestionEntity>)

    fun getLocalQuestionDetail(id: Int): QuestionEntity

    fun saveLocalAnswerList(answerList: List<AnswerEntity>)

    fun getLocalAnswerList(id: Int): List<AnswerEntity>

    fun getLocalQuestionList(): List<QuestionEntity>

    fun getRemoteQuestionDetail(id: Int): Flowable<QuestionEntity>

    fun getRemoteAnswerList(id: Int): Flowable<List<AnswerEntity>>

    fun postCreateQuestion(question: QuestionEntity): Flowable<QuestionEntity>

    fun postCreateAnswer(answer: AnswerEntity): Flowable<AnswerEntity>
}
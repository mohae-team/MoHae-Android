package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.QuestionDto
import com.mohaeyo.data.entity.AnswerData
import com.mohaeyo.data.entity.QuestionData
import com.mohaeyo.data.local.database.entity.Answer
import com.mohaeyo.data.local.database.entity.Question
import io.reactivex.Flowable

interface QADataSource {
    fun getRemoteQuestionList(): Flowable<List<QuestionDto>>

    fun saveLocalQuestion(question: Question)

    fun saveLocalQuestionList(questionList: List<Question>)

    fun getLocalQuestionDetail(id: Int): Question

    fun saveLocalAnswerList(answerList: List<Answer>)

    fun getLocalAnswerList(id: Int): List<Answer>

    fun getLocalQuestionList(): List<Question>

    fun getRemoteQuestionDetail(id: Int): Flowable<QuestionDto>

    fun getRemoteAnswerList(id: Int): Flowable<List<AnswerData>>

    fun postCreateQuestion(question: QuestionData): Flowable<QuestionDto>

    fun postCreateAnswer(answer: AnswerData): Flowable<AnswerData>
}
package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.AnswerDto
import com.mohaeyo.data.dto.QuestionDto
import com.mohaeyo.data.entity.AnswerData
import com.mohaeyo.data.entity.QuestionData
import com.mohaeyo.data.local.database.dao.AnswerDao
import com.mohaeyo.data.local.database.dao.QuestionDao
import com.mohaeyo.data.local.database.entity.Answer
import com.mohaeyo.data.local.database.entity.Question
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.toImageRequestBody
import io.reactivex.Flowable
import okhttp3.MultipartBody

class QADataSourceImpl(
    private val api: Api,
    private val questionDao: QuestionDao,
    private val answerDao: AnswerDao
): QADataSource {
    override fun getRemoteQuestionList(): Flowable<List<QuestionDto>>
            = api.getQuestionList()

    override fun saveLocalQuestion(question: Question)
            = questionDao.saveQuestion(question)

    override fun saveLocalQuestionList(questionList: List<Question>)
            = questionDao.saveQuestion(*questionList.toTypedArray())

    override fun getLocalQuestionDetail(id: Int): Question
            = questionDao.getQuestion(id)

    override fun saveLocalAnswerList(answerList: List<Answer>)
            = answerDao.saveAnswer(*answerList.toTypedArray())

    override fun getLocalAnswerList(id: Int): List<Answer>
            = answerDao.getAnswerList(id)

    override fun getLocalQuestionList(): List<Question>
            = questionDao.getQuestionList()

    override fun getRemoteQuestionDetail(id: Int): Flowable<QuestionDto>
            = api.getQuestionDetail(id)

    override fun getRemoteAnswerList(id: Int): Flowable<List<AnswerData>>
            = api.getAnswerList(id)

    override fun postCreateQuestion(question: QuestionData): Flowable<QuestionDto>
            = api.postCreateQuestion(
        title = MultipartBody.Part.createFormData("title", question.title),
        summary = MultipartBody.Part.createFormData("summary", question.summary),
        description = MultipartBody.Part.createFormData("description", question.description),
        imageFile = MultipartBody.Part.createFormData("imageFile", question.imageFile.name, question.imageFile.toImageRequestBody())
    )

    override fun postCreateAnswer(answer: AnswerData): Flowable<AnswerData>
            = api.postCreateAnswer(AnswerDto(answer.questionId, answer.answer))
}
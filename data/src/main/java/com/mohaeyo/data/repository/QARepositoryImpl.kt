package com.mohaeyo.data.repository

import com.mohaeyo.data.datasource.QADataSource
import com.mohaeyo.data.mapper.AnswerDataMapper
import com.mohaeyo.data.mapper.QuestionDataMapper
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.repository.QARepository
import io.reactivex.Flowable

class QARepositoryImpl(
    private val datasource: QADataSource,
    private val questionMapper: QuestionDataMapper,
    private val answerMapper: AnswerDataMapper
): QARepository {
    override fun getRemoteQuestionList(): Flowable<List<QuestionEntity>>
            = datasource.getRemoteQuestionList().map { list -> list.map { questionMapper.mapDtoToEntity(it) } }

    override fun saveLocalQuestion(question: QuestionEntity)
            = datasource.saveLocalQuestion(questionMapper.mapEntityToDb(question))

    override fun saveLocalQuestionList(questionList: List<QuestionEntity>)
            = datasource.saveLocalQuestionList(questionList.map { questionMapper.mapEntityToDb(it) })

    override fun getLocalQuestionDetail(id: Int): QuestionEntity
            = questionMapper.mapDbToEntity(datasource.getLocalQuestionDetail(id))

    override fun saveLocalAnswerList(answerList: List<AnswerEntity>)
            = datasource.saveLocalAnswerList(answerList.map { answerMapper.mapEntityToDb(it) })

    override fun getLocalQuestionList(): List<QuestionEntity>
            = datasource.getLocalQuestionList().map { questionMapper.mapDbToEntity(it) }

    override fun getLocalAnswerList(id: Int): List<AnswerEntity>
            = datasource.getLocalAnswerList(id).map { answerMapper.mapDbToEntity(it) }

    override fun getRemoteQuestionDetail(id: Int): Flowable<QuestionEntity>
            = datasource.getRemoteQuestionDetail(id).map { questionMapper.mapDtoToEntity(it) }

    override fun getRemoteAnswerList(id: Int): Flowable<List<AnswerEntity>>
            = datasource.getRemoteAnswerList(id).map { list -> list.map { answerMapper.mapDataToEntity(it) } }

    override fun postCreateQuestion(question: QuestionEntity): Flowable<QuestionEntity>
            = datasource.postCreateQuestion(questionMapper.mapFrom(question)).map { questionMapper.mapDtoToEntity(it) }

    override fun postCreateAnswer(answer: AnswerEntity): Flowable<AnswerEntity>
            = datasource.postCreateAnswer(answerMapper.mapFrom(answer)).map { answerMapper.mapDataToEntity(it) }
}
package com.mohaeyo.domain.service

import com.mohaeyo.domain.BaseTest
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.handler.QAErrorHandler
import com.mohaeyo.domain.repository.QARepository
import com.mohaeyo.domain.usecase.*
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`

class QAServiceTest: BaseTest() {
    @Mock
    private lateinit var qaRepository: QARepository
    @Mock
    private lateinit var qaErrorHandler: QAErrorHandler

    private lateinit var qaService: QAService

    private lateinit var getQuestionListUseCase: GetQuestionListUseCase
    private lateinit var getQuestionDetailUseCase: GetQuestionDetailUseCase
    private lateinit var createQuestionUseCase: CreateQuestionUseCase
    private lateinit var getAnswerListUseCase: GetAnswerListUseCase
    private lateinit var createAnswerUseCase: CreateAnswerUseCase

    @Before
    fun init() {
        qaService = QAServiceImpl(qaRepository, qaErrorHandler)

        getQuestionListUseCase = GetQuestionListUseCase(qaService, CompositeDisposable())
        getQuestionDetailUseCase = GetQuestionDetailUseCase(qaService, CompositeDisposable())
        createQuestionUseCase = CreateQuestionUseCase(qaService, CompositeDisposable())
        getAnswerListUseCase = GetAnswerListUseCase(qaService, CompositeDisposable())
        createAnswerUseCase = CreateAnswerUseCase(qaService, CompositeDisposable())
    }

    @Test
    fun `질문 리스트 가져오기 성공 테스트`() {
        `when`(qaRepository.getRemoteQuestionList())
            .thenReturn(Flowable.just(emptyList()))

        getQuestionListUseCase.createFlowable(
            Unit
        ).test().assertValue(emptyList<QuestionEntity>() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `질문 리스트 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(qaRepository.getRemoteQuestionList())
            .thenReturn(Flowable.error(throwable))

        `when`(qaRepository.getLocalQuestionList())
            .thenReturn(emptyList())

        `when`(qaErrorHandler.getQuestionListErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getQuestionListUseCase.createFlowable(
            Unit
        ).test().assertValue(emptyList<QuestionEntity>() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `답변 리스트 가져오기 성공 테스트`() {
        `when`(qaRepository.getRemoteAnswerList(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.just(emptyList()))

        getAnswerListUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(emptyList<AnswerEntity>() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `답변 리스트 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(qaRepository.getRemoteAnswerList(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.error(throwable))

        `when`(qaRepository.getLocalAnswerList(ArgumentMatchers.anyInt()))
            .thenReturn(emptyList())

        `when`(qaErrorHandler.getAnswerListErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getAnswerListUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(emptyList<AnswerEntity>() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `질문 개별정보 가져오기 성공 테스트`() {
        `when`(qaRepository.getRemoteQuestionDetail(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.just(QuestionEntity()))

        getQuestionDetailUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(QuestionEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `질문 개별정보 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(qaRepository.getRemoteQuestionDetail(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.error(throwable))

        `when`(qaRepository.getLocalQuestionDetail(ArgumentMatchers.anyInt()))
            .thenReturn(QuestionEntity())

        `when`(qaErrorHandler.getQuestionDetailErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getQuestionDetailUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(QuestionEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `질문 만들기 성공 테스트`() {
        `when`(qaRepository.postCreateQuestion(QuestionEntity()))
            .thenReturn(Flowable.just(QuestionEntity()))

        createQuestionUseCase.createFlowable(
            QuestionEntity()
        ).test().assertValue(QuestionEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `질문 만들기 실패 테스트`() {
        val throwable = Throwable()

        `when`(qaRepository.postCreateQuestion(QuestionEntity()))
            .thenReturn(Flowable.error(throwable))

        `when`(qaErrorHandler.createQuestionErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        createQuestionUseCase.createFlowable(
            QuestionEntity()
        ).test().assertValue(QuestionEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `답변 만들기 성공 테스트`() {
        `when`(qaRepository.postCreateAnswer(AnswerEntity()))
            .thenReturn(Flowable.just(AnswerEntity()))

        createAnswerUseCase.createFlowable(
            AnswerEntity()
        ).test().assertValue(AnswerEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `답변 만들기 실패 테스트`() {
        val throwable = Throwable()

        `when`(qaRepository.postCreateAnswer(AnswerEntity()))
            .thenReturn(Flowable.error(throwable))

        `when`(qaErrorHandler.createAnswerErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        createAnswerUseCase.createFlowable(
            AnswerEntity()
        ).test().assertValue(AnswerEntity() to ErrorHandlerEntity(isSuccess = false))
    }
}
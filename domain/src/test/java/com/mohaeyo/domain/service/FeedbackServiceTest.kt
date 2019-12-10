package com.mohaeyo.domain.service

import com.mohaeyo.domain.BaseTest
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.handler.FeedbackErrorHandler
import com.mohaeyo.domain.repository.FeedbackRepository
import com.mohaeyo.domain.usecase.*
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`

class FeedbackServiceTest: BaseTest() {
    @Mock
    private lateinit var feedbackRepository: FeedbackRepository
    @Mock
    private lateinit var feedbackErrorHandler: FeedbackErrorHandler

    private lateinit var feedbackService: FeedbackService

    private lateinit var getFeedbackListUseCase: GetFeedbackListUseCase
    private lateinit var getFeedbackDetailUseCase: GetFeedbackDetailUseCase
    private lateinit var createFeedbackUseCase: CreateFeedbackUseCase
    private lateinit var likeFeedbackUseCase: LikeFeedbackUseCase
    private lateinit var hateFeedbackUseCase: HateFeedbackUseCase

    @Before
    fun init() {
        feedbackService = FeedbackServiceImpl(feedbackRepository, feedbackErrorHandler)

        getFeedbackListUseCase = GetFeedbackListUseCase(feedbackService, CompositeDisposable())
        getFeedbackDetailUseCase = GetFeedbackDetailUseCase(feedbackService, CompositeDisposable())
        createFeedbackUseCase = CreateFeedbackUseCase(feedbackService, CompositeDisposable())
        likeFeedbackUseCase = LikeFeedbackUseCase(feedbackService, CompositeDisposable())
        hateFeedbackUseCase = HateFeedbackUseCase(feedbackService, CompositeDisposable())
    }

    @Test
    fun `검토 리스트 가져오기 성공 테스트`() {
        `when`(feedbackRepository.getRemoteFeedbackList())
            .thenReturn(Flowable.just(emptyList()))

        getFeedbackListUseCase.createFlowable(
            Unit
        ).test().assertValue(emptyList<FeedbackEntity>() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `검토 리스트 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(feedbackRepository.getRemoteFeedbackList())
            .thenReturn(Flowable.error(throwable))

        `when`(feedbackRepository.getLocalFeedbackList())
            .thenReturn(emptyList())

        `when`(feedbackErrorHandler.getListErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getFeedbackListUseCase.createFlowable(
            Unit
        ).test().assertValue(emptyList<FeedbackEntity>() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `검토 개별정보 가져오기 성공 테스트`() {
        `when`(feedbackRepository.getRemoteFeedbackDetail(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.just(FeedbackEntity()))

        getFeedbackDetailUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(FeedbackEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `검토 개별정보 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(feedbackRepository.getRemoteFeedbackDetail(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.error(throwable))

        `when`(feedbackRepository.getLocalFeedbackDetail(ArgumentMatchers.anyInt()))
            .thenReturn(FeedbackEntity())

        `when`(feedbackErrorHandler.getDetailErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getFeedbackDetailUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(FeedbackEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `검토 만들기 성공 테스트`() {
        `when`(feedbackRepository.createFeedback(FeedbackEntity()))
            .thenReturn(Flowable.just(FeedbackEntity()))

        createFeedbackUseCase.createFlowable(
            FeedbackEntity()
        ).test().assertValue(FeedbackEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `검토 만들기 실패 테스트`() {
        val throwable = Throwable()

        `when`(feedbackRepository.createFeedback(FeedbackEntity()))
            .thenReturn(Flowable.error(throwable))

        `when`(feedbackErrorHandler.createErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        createFeedbackUseCase.createFlowable(
            FeedbackEntity()
        ).test().assertValue(FeedbackEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `검토 좋아요하기 성공 테스트`() {
        `when`(feedbackRepository.likeFeedback(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.just(FeedbackEntity()))

        likeFeedbackUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(FeedbackEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `검토 좋아요하기 실패 테스트`() {
        val throwable = Throwable()

        `when`(feedbackRepository.likeFeedback(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.error(throwable))

        `when`(feedbackRepository.getLocalFeedbackDetail(ArgumentMatchers.anyInt()))
            .thenReturn(FeedbackEntity())

        `when`(feedbackErrorHandler.likeErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        likeFeedbackUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(FeedbackEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `검토 싫어요하기 성공 테스트`() {
        `when`(feedbackRepository.hateFeedback(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.just(FeedbackEntity()))

        hateFeedbackUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(FeedbackEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `검토 싫어요하기 실패 테스트`() {
        val throwable = Throwable()

        `when`(feedbackRepository.hateFeedback(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.error(throwable))

        `when`(feedbackRepository.getLocalFeedbackDetail(ArgumentMatchers.anyInt()))
            .thenReturn(FeedbackEntity())

        `when`(feedbackErrorHandler.hateErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        hateFeedbackUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(FeedbackEntity() to ErrorHandlerEntity(isSuccess = false))
    }
}
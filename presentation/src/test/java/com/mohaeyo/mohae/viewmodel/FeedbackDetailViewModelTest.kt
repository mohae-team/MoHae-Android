package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.GetFeedbackDetailUseCase
import com.mohaeyo.domain.usecase.HateFeedbackUseCase
import com.mohaeyo.domain.usecase.LikeFeedbackUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.model.FeedbackModel
import com.mohaeyo.mohae.viewmodel.main.feedback.detail.FeedbackDetailViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class FeedbackDetailViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getFeedbackDetailUseCase: GetFeedbackDetailUseCase
    @Mock
    private lateinit var likeFeedbackUseCase: LikeFeedbackUseCase
    @Mock
    private lateinit var hateFeedbackUseCase: HateFeedbackUseCase

    private lateinit var feedbackDetailViewModel: FeedbackDetailViewModel
    private lateinit var feedbackMapper: FeedbackMapper

    @Before
    fun init() {
        feedbackMapper = FeedbackMapper()
        feedbackDetailViewModel = FeedbackDetailViewModel(
            getFeedbackDetailUseCase, likeFeedbackUseCase, hateFeedbackUseCase, feedbackMapper)
    }

    @Test
    fun `검토정보를 가져오는 행위 성공 알림 테스트`() {
        feedbackDetailViewModel.run {
            val result = FeedbackModel(id = 100)
            getDetailSuccess(result)

            selectedFeedbackItem.test().assertValue(result)
        }
    }

    @Test
    fun `검토정보를 가져오는 행위 실패 알림 테스트`() {
        feedbackDetailViewModel.run {
            val result = FeedbackModel(id = 100)
            val failMessage = "검토 정보를 가져올 수 없습니다."
            getDetailFail(failMessage, result)

            createToastEvent.test().assertValue(failMessage)
            selectedFeedbackItem.test().assertValue(result)
        }
    }

    @Test
    fun `검토를 좋아요하는 행위 성공 알림 테스트`() {
        feedbackDetailViewModel.run {
            val result = FeedbackModel(id = 100)
            likeSuccess(result)

            selectedFeedbackItem.test().assertValue(result)
        }
    }

    @Test
    fun `검토를 좋아요하는 행위 실패 알림 테스트`() {
        feedbackDetailViewModel.run {
            val result = FeedbackModel(id = 100)
            val failMessage = "검토 정보를 좋아요할 수 없습니다."
            likeFail(failMessage, result)

            createToastEvent.test().assertValue(failMessage)
            selectedFeedbackItem.test().assertValue(result)
        }
    }

    @Test
    fun `검토를 싫어요하는 행위 성공 알림 테스트`() {
        feedbackDetailViewModel.run {
            val result = FeedbackModel(id = 100)
            hateSuccess(result)

            selectedFeedbackItem.test().assertValue(result)
        }
    }

    @Test
    fun `검토를 싫어요하는 행위 실패 알림 테스트`() {
        feedbackDetailViewModel.run {
            val result = FeedbackModel(id = 100)
            val failMessage = "검토 정보를 싫어요할 수 없습니다."
            hateFail(failMessage, result)

            createToastEvent.test().assertValue(failMessage)
            selectedFeedbackItem.test().assertValue(result)
        }
    }
}
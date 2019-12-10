package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.CreateFeedbackUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.model.FeedbackModel
import com.mohaeyo.mohae.viewmodel.main.feedback.doc.FeedbackDocViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class FeedbackDocViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var createFeedbackUseCase: CreateFeedbackUseCase

    private lateinit var feedbackDocViewModel: FeedbackDocViewModel
    private lateinit var feedbackMapper: FeedbackMapper

    @Before
    fun init() {
        feedbackMapper = FeedbackMapper()
        feedbackDocViewModel = FeedbackDocViewModel(createFeedbackUseCase, feedbackMapper)
    }

    @Test
    fun `검토 만드는 행위 성공 알림 테스트`() {
        feedbackDocViewModel.run {
            createSuccess(FeedbackModel(id = 100))

            feedbackModel.test().assertValue(FeedbackModel(id = 100))
            startDocToListEvent.test().assertHasValue()
        }
    }

    @Test
    fun `검토 만드는 행위 실패 알림 테스트`() {
        feedbackDocViewModel.run {
            val failMessage = "해당 검토를 만들 수 없습니다."
            createFail(failMessage, FeedbackModel(id = 100))

            createToastEvent.test().assertValue(failMessage)
            feedbackModel.test().assertValue(FeedbackModel(id = 100))
        }
    }

    @Test
    fun `이미지 가져오는 이벤트 테스트`() {
        feedbackDocViewModel.run {
            clickSetImage()
            setFeedbackImageEvent.test().assertHasValue()
        }
    }
}
package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.GetFeedbackListUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.model.FeedbackModel
import com.mohaeyo.mohae.viewmodel.main.feedback.list.FeedbackListViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class FeedbackListViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getFeedbackListUseCase: GetFeedbackListUseCase

    private lateinit var feedbackListViewModel: FeedbackListViewModel
    private lateinit var feedbackMapper: FeedbackMapper

    @Before
    fun init() {
        feedbackMapper = FeedbackMapper()
        feedbackListViewModel = FeedbackListViewModel(getFeedbackListUseCase, feedbackMapper)
    }

    @Test
    fun `검토 리스트 가져오는 행위 성공 알림 테스트`() {
        feedbackListViewModel.run {
            getListSuccess(emptyList())

            feedbackList.test().assertValue(
                ArrayList(emptyList<FeedbackModel>())
            )
            listAnimationEvent.test().assertHasValue()
        }
    }

    @Test
    fun `그룹 리스트 가져오는 행위 실패 알림 테스트`() {
        feedbackListViewModel.run {
            val failMessage = "검토 리스트를 가져올 수 없습니다."

            getListFail(failMessage, emptyList())

            createToastEvent.test().assertValue(failMessage)
            feedbackList.test().assertValue(
                ArrayList(emptyList<FeedbackModel>())
            )
            listAnimationEvent.test().assertHasValue()
        }
    }
}
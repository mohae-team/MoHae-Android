package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.GetAnswerListUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.AnswerMapper
import com.mohaeyo.mohae.model.AnswerModel
import com.mohaeyo.mohae.viewmodel.main.qa.answerList.QAAnswerListViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class QAAnswerListViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getAnswerListUseCase: GetAnswerListUseCase

    private lateinit var qaAnswerListViewModel: QAAnswerListViewModel
    private lateinit var answerMapper: AnswerMapper

    @Before
    fun init() {
        answerMapper = AnswerMapper()
        qaAnswerListViewModel = QAAnswerListViewModel(getAnswerListUseCase, answerMapper)
    }

    @Test
    fun `답변 리스트 가져오는 행위 성공 알림 테스트`() {
        qaAnswerListViewModel.run {
            getListSuccess(emptyList())

            answerList.test().assertValue(
                ArrayList(emptyList<AnswerModel>())
            )
            listAnimationEvent.test().assertHasValue()
        }
    }

    @Test
    fun `답변 리스트 가져오는 행위 실패 알림 테스트`() {
        qaAnswerListViewModel.run {
            val failMessage = "답변 리스트를 가져올 수 없습니다."

            getListFail(failMessage, emptyList())

            createToastEvent.test().assertValue(failMessage)
            answerList.test().assertValue(
                ArrayList(emptyList<AnswerModel>())
            )
            listAnimationEvent.test().assertHasValue()
        }
    }
}
package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.GetQuestionListUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.model.QuestionModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionList.QAQuestionListViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class QAQuestionListViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getQuestionListUseCase: GetQuestionListUseCase

    private lateinit var qaQuestionListViewModel: QAQuestionListViewModel
    private lateinit var questionMapper: QuestionMapper

    @Before
    fun init() {
        questionMapper = QuestionMapper()
        qaQuestionListViewModel = QAQuestionListViewModel(getQuestionListUseCase, questionMapper)
    }

    @Test
    fun `질문 리스트 가져오는 행위 성공 알림 테스트`() {
        qaQuestionListViewModel.run {
            getListSuccess(emptyList())

            questionList.test().assertValue(
                ArrayList(emptyList<QuestionModel>())
            )
            listAnimationEvent.test().assertHasValue()
        }
    }

    @Test
    fun `질문 리스트 가져오는 행위 실패 알림 테스트`() {
        qaQuestionListViewModel.run {
            val failMessage = "질문 리스트를 가져올 수 없습니다."

            getListFail(failMessage, emptyList())

            createToastEvent.test().assertValue(failMessage)
            questionList.test().assertValue(
                ArrayList(emptyList<QuestionModel>())
            )
            listAnimationEvent.test().assertHasValue()
        }
    }
}
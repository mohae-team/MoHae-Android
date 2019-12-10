package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.GetQuestionDetailUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.model.QuestionModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDetail.QAQuestionDetailViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class QAQuestionDetailViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getQuestionDetailUseCase: GetQuestionDetailUseCase

    private lateinit var qaQuestionDetailViewModel: QAQuestionDetailViewModel
    private lateinit var questionMapper: QuestionMapper

    @Before
    fun init() {
        questionMapper = QuestionMapper()
        qaQuestionDetailViewModel = QAQuestionDetailViewModel(getQuestionDetailUseCase, questionMapper)
    }

    @Test
    fun `질문 개별정보 가져오는 행위 성공 알림 테스트`() {
        qaQuestionDetailViewModel.run {
            getDetailSuccess(QuestionModel())
            selectedQuestionItem.test().assertValue(QuestionModel())
        }
    }

    @Test
    fun `질문 리스트 가져오는 행위 실패 알림 테스트`() {
        qaQuestionDetailViewModel.run {
            val failMessage = "질문정보를 가져올 수 없습니다."

            getDetailFail(failMessage, QuestionModel())
            createToastEvent.test().assertValue(failMessage)
            selectedQuestionItem.test().assertValue(QuestionModel())
        }
    }
}
package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.CreateQuestionUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.model.QuestionModel
import com.mohaeyo.mohae.viewmodel.main.qa.questionDoc.QAQuestionDocViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class QAQuestionDocViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var createQuestionUseCase: CreateQuestionUseCase

    private lateinit var questionDocViewModel: QAQuestionDocViewModel
    private lateinit var questionMapper: QuestionMapper

    @Before
    fun init() {
        questionMapper = QuestionMapper()
        questionDocViewModel = QAQuestionDocViewModel(createQuestionUseCase, questionMapper)
    }

    @Test
    fun `질문 만드는 행위 성공 알림 테스트`() {
        questionDocViewModel.run {
            createSuccess(QuestionModel(id = 100))
            questionModel.test().assertValue(QuestionModel(id = 100))
            startDocToListEvent.test().assertHasValue()
        }
    }

    @Test
    fun `질문 만드는 행위 실패 알림 테스트`() {
        questionDocViewModel.run {
            val failMessage = "해당 그룹을 만들 수 없습니다."
            createFail(failMessage)

            createToastEvent.test().assertValue(failMessage)
        }
    }
}
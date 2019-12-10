package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.CreateAnswerUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.AnswerMapper
import com.mohaeyo.mohae.model.AnswerModel
import com.mohaeyo.mohae.viewmodel.main.qa.answerDoc.QAAnswerDocViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class QAAnswerDocViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var createAnswerUseCase: CreateAnswerUseCase

    private lateinit var answerDocViewModel: QAAnswerDocViewModel
    private lateinit var answerMapper: AnswerMapper

    @Before
    fun init() {
        answerMapper = AnswerMapper()
        answerDocViewModel = QAAnswerDocViewModel(createAnswerUseCase, answerMapper)
    }

    @Test
    fun `답변 만드는 행위 성공 알림 테스트`() {
        answerDocViewModel.run {
            createSuccess(AnswerModel(id = 100))
            answerModel.test().assertValue(AnswerModel(id = 100))
            startDocToListEvent.test().assertHasValue()
        }
    }

    @Test
    fun `답변 만드는 행위 실패 알림 테스트`() {
        answerDocViewModel.run {
            val failMessage = "해당 그룹을 만들 수 없습니다."
            createFail(failMessage)

            createToastEvent.test().assertValue(failMessage)
        }
    }
}
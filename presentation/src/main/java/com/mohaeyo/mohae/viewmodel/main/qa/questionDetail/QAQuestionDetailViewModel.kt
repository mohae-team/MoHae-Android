package com.mohaeyo.mohae.viewmodel.main.qa.questionDetail

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.usecase.GetQuestionDetailUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.model.QuestionModel
import io.reactivex.subscribers.DisposableSubscriber

class QAQuestionDetailViewModel(
    private val getQuestionDetailUseCase: GetQuestionDetailUseCase,
    private val questionMapper: QuestionMapper
): BaseViewModel() {

    val selectedQuestionId = MutableLiveData<Int>()

    val selectedQuestionItem = MutableLiveData<QuestionModel>().apply {
        value = QuestionModel()
    }

    val startDetailToQuestionListEvent = SingleLiveEvent<Unit>()
    val startDetailToAnswerListEvent = SingleLiveEvent<Unit>()

    fun getQuestionDetail() {
        getQuestionDetailUseCase.execute(selectedQuestionId.value!!, object: DisposableSubscriber<Pair<QuestionEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<QuestionEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getDetailSuccess(questionMapper.mapEntityToModel(t.first))
                else getDetailFail(t.second.message, questionMapper.mapEntityToModel(t.first))
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun getDetailSuccess(questionModel: QuestionModel) {
        selectedQuestionItem.value = questionModel
    }

    fun getDetailFail(message: String, questionModel: QuestionModel) {
        createToastEvent.value = message
        selectedQuestionItem.value = questionModel
    }

    fun clickDetailToList() {
        startDetailToQuestionListEvent.call()
    }

    fun clickAnswers() {
        startDetailToAnswerListEvent.call()
    }
}
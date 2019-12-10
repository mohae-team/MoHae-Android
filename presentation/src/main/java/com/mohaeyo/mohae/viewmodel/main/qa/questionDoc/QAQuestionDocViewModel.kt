package com.mohaeyo.mohae.viewmodel.main.qa.questionDoc

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.usecase.CreateQuestionUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.model.QuestionModel
import io.reactivex.subscribers.DisposableSubscriber

class QAQuestionDocViewModel(
    private val createQuestionUseCase: CreateQuestionUseCase,
    private val questionMapper: QuestionMapper
): BaseViewModel() {
    val questionModel = MutableLiveData<QuestionModel>().apply {
        value = QuestionModel()
    }

    val startDocToListEvent = SingleLiveEvent<Unit>()
    val titleErrorEvent = SingleLiveEvent<String>()
    val summaryErrorEvent = SingleLiveEvent<String>()
    val descriptionErrorEvent = SingleLiveEvent<String>()
    val setImageEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickSetImage() {
        setImageEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }

    fun clickPostQuestion() {
        createQuestionUseCase.execute(questionMapper.mapFrom(questionModel.value!!), object: DisposableSubscriber<Pair<QuestionEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<QuestionEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) createSuccess(questionMapper.mapEntityToModel(t.first))
                else createFail(t.second.message)
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun createSuccess(question: QuestionModel) {
        questionModel.value = question
        startDocToListEvent.call()
    }

    fun createFail(message: String) {
        createToastEvent.value = message
    }
}
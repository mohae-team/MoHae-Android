package com.mohaeyo.mohae.viewmodel.main.qa.answerDoc

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.usecase.CreateAnswerUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.AnswerMapper
import com.mohaeyo.mohae.model.AnswerModel
import io.reactivex.subscribers.DisposableSubscriber

class QAAnswerDocViewModel(
    private val createAnswerUseCase: CreateAnswerUseCase,
    private val answerMapper: AnswerMapper
): BaseViewModel() {
    val answerModel = MutableLiveData<AnswerModel>().apply { value = AnswerModel() }

    val startDocToListEvent = SingleLiveEvent<Unit>()
    val answerErrorEvent = SingleLiveEvent<String>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickPostAnswer() {
        createAnswerUseCase.execute(answerMapper.mapFrom(answerModel.value!!), object: DisposableSubscriber<Pair<AnswerEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<AnswerEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) createSuccess(answerMapper.mapEntityToModel(t.first))
                else createFail(t.second.message)
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun createSuccess(answerModel: AnswerModel) {
        this.answerModel.value = answerModel
        startDocToListEvent.call()
    }

    fun createFail(message: String) {
        createToastEvent.value = message
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
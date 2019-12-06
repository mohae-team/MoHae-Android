package com.mohaeyo.mohae.viewmodel.main.qa.answerList

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.usecase.GetAnswerListUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.AnswerMapper
import com.mohaeyo.mohae.model.AnswerModel
import io.reactivex.subscribers.DisposableSubscriber

class QAAnswerListViewModel(
    private val getAnswerListUseCase: GetAnswerListUseCase,
    private val answerMapper: AnswerMapper
): BaseViewModel() {
    val selectedQuestionId = MutableLiveData<Int>()
    val answerList = MutableLiveData<ArrayList<AnswerModel>>().apply {
        value = ArrayList(emptyList())
    }

    val startListToDetailEvent = SingleLiveEvent<Unit>()
    val startListToDocEvent = SingleLiveEvent<Unit>()
    val listAnimationEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> getAnswerList()
        }
    }

    fun clickListToDetail() {
        startListToDetailEvent.call()
    }

    fun clickWriteAnswer() {
        startListToDocEvent.call()
    }

    private fun getAnswerList() {
        getAnswerListUseCase.execute(selectedQuestionId.value!!, object: DisposableSubscriber<Pair<List<AnswerEntity>, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<List<AnswerEntity>, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getListSuccess(t.first.map { answerMapper.mapEntityToModel(it) })
                else getListFail(t.second.message, t.first.map { answerMapper.mapEntityToModel(it) })
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    private fun getListSuccess(answerList: List<AnswerModel>) {
        this.answerList.value = ArrayList(answerList)
        listAnimationEvent.call()
    }

    private fun getListFail(message: String, answerList: List<AnswerModel>) {
        this.answerList.value = ArrayList(answerList)
        createToastEvent.value = message
        listAnimationEvent.call()
    }
}
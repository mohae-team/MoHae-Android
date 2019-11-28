package com.mohaeyo.mohae.viewmodel.main.qa.questionList

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.usecase.GetQuestionListUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.QuestionMapper
import com.mohaeyo.mohae.model.QuestionModel
import io.reactivex.subscribers.DisposableSubscriber

class QAQuestionListViewModel(
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val questionMapper: QuestionMapper
): BaseViewModel() {
    val questionList = MutableLiveData<ArrayList<QuestionModel>>().apply {
        value = ArrayList(emptyList())
    }

    val startListToDetailEvent = SingleLiveEvent<QuestionModel>()
    val startListToDocEvent = SingleLiveEvent<Unit>()

    init {
        getQuestionList()
    }

    fun getQuestionList() {
        getQuestionListUseCase.execute(Unit, object: DisposableSubscriber<Pair<List<QuestionEntity>, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<List<QuestionEntity>, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getListSuccess(t.first.map { questionMapper.mapEntityToModel(it) })
                else getListFail(t.second.message, t.first.map { questionMapper.mapEntityToModel(it) })
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    private fun getListSuccess(questionList: List<QuestionModel>) {
        this.questionList.value = ArrayList(questionList)
    }

    private fun getListFail(message: String, questionList: List<QuestionModel>) {
        createToastEvent.value = message
        this.questionList.value = ArrayList(questionList)
    }

    fun clickListToDoc() {
        startListToDocEvent.call()
    }

    fun clickQuestionItem(question: QuestionModel) {
        startListToDetailEvent.value = question
    }
}
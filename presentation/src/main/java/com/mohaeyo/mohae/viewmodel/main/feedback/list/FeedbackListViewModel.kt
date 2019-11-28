package com.mohaeyo.mohae.viewmodel.main.feedback.list

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.usecase.GetFeedbackListUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.model.FeedbackModel
import io.reactivex.subscribers.DisposableSubscriber

class FeedbackListViewModel(
    private val getFeedbackListUseCase: GetFeedbackListUseCase,
    private val feedbackMapper: FeedbackMapper
): BaseViewModel() {
    val feedbackList = MutableLiveData<ArrayList<FeedbackModel>>().apply {
        value = ArrayList(emptyList())
    }

    val startListToDetailEvent = SingleLiveEvent<FeedbackModel>()
    val startListToDocEvent = SingleLiveEvent<Unit>()

    init {
        getFeedbackListUseCase.execute(Unit, object: DisposableSubscriber<Pair<List<FeedbackEntity>, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<List<FeedbackEntity>, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getListSuccess(t.first.map { feedbackMapper.mapEntityToModel(it) })
                else getListFail(t.second.message, t.first.map { feedbackMapper.mapEntityToModel(it) })
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    private fun getListSuccess(feedbackList: List<FeedbackModel>) {
        this.feedbackList.value = ArrayList(feedbackList)
    }

    private fun getListFail(message: String, feedbackList: List<FeedbackModel>) {
        createToastEvent.value = message
        this.feedbackList.value = ArrayList(feedbackList)
    }

    fun clickListToDoc() {
        startListToDocEvent.call()
    }

    fun clickFeedbackItem(feedback: FeedbackModel) {
        startListToDetailEvent.value = feedback
    }
}
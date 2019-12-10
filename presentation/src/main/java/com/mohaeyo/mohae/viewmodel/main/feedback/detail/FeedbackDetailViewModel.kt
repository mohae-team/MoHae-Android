package com.mohaeyo.mohae.viewmodel.main.feedback.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.usecase.GetFeedbackDetailUseCase
import com.mohaeyo.domain.usecase.HateFeedbackUseCase
import com.mohaeyo.domain.usecase.LikeFeedbackUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.model.FeedbackModel
import io.reactivex.subscribers.DisposableSubscriber

class FeedbackDetailViewModel(
    private val getFeedbackDetailUseCase: GetFeedbackDetailUseCase,
    private val likeFeedbackUseCase: LikeFeedbackUseCase,
    private val hateFeedbackUseCase: HateFeedbackUseCase,
    private val feedbackMapper: FeedbackMapper
): BaseViewModel() {
    val selectedFeedbackId = MutableLiveData<Int>()
    val selectedFeedbackItem = MutableLiveData<FeedbackModel>().apply {
        value = FeedbackModel()
    }

    val startDetailToListEvent = SingleLiveEvent<Unit>()
    val startDetailToDialogEvent = SingleLiveEvent<Unit>()
    val closeDialog = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> getFeedbackDetail()
        }
    }

    fun clickDetailToList() {
        startDetailToListEvent.call()
    }

    fun clickLikeOrHate() {
        startDetailToDialogEvent.call()
    }

    fun clickLike() {
        likeFeedbackUseCase.execute(selectedFeedbackId.value!!, object: DisposableSubscriber<Pair<FeedbackEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<FeedbackEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) likeSuccess(feedbackMapper.mapEntityToModel(t.first))
                else likeFail(t.second.message, feedbackMapper.mapEntityToModel(t.first))
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
        closeDialog.call()
    }

    fun clickHate() {
        hateFeedbackUseCase.execute(selectedFeedbackId.value!!, object: DisposableSubscriber<Pair<FeedbackEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<FeedbackEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) hateSuccess(feedbackMapper.mapEntityToModel(t.first))
                else hateFail(t.second.message, feedbackMapper.mapEntityToModel(t.first))
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
        closeDialog.call()
    }

    private fun getFeedbackDetail() {
        getFeedbackDetailUseCase.execute(selectedFeedbackId.value!!, object: DisposableSubscriber<Pair<FeedbackEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<FeedbackEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getDetailSuccess(feedbackMapper.mapEntityToModel(t.first))
                else getDetailFail(t.second.message, feedbackMapper.mapEntityToModel(t.first))
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun getDetailSuccess(feedbackModel: FeedbackModel) {
        selectedFeedbackItem.value = feedbackModel
    }

    fun getDetailFail(message: String, feedbackModel: FeedbackModel) {
        createToastEvent.value = message
        selectedFeedbackItem.value = feedbackModel
    }

    fun likeSuccess(feedbackModel: FeedbackModel) {
        selectedFeedbackItem.value = feedbackModel
    }

    fun likeFail(message: String, feedbackModel: FeedbackModel) {
        createToastEvent.value = message
        selectedFeedbackItem.value = feedbackModel
    }

    fun hateSuccess(feedbackModel: FeedbackModel) {
        selectedFeedbackItem.value = feedbackModel
    }

    fun hateFail(message: String, feedbackModel: FeedbackModel) {
        createToastEvent.value = message
        selectedFeedbackItem.value = feedbackModel
    }
}
package com.mohaeyo.mohae.viewmodel.main.feedback.doc

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.usecase.CreateFeedbackUseCase
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.FeedbackMapper
import com.mohaeyo.mohae.model.FeedbackModel
import com.mohaeyo.mohae.model.MapMakerModel
import io.reactivex.subscribers.DisposableSubscriber

class FeedbackDocViewModel(
    private val createFeedbackUseCase: CreateFeedbackUseCase,
    private val feedbackMapper: FeedbackMapper
): BaseLocationViewModel() {

    val feedbackModel = MutableLiveData<FeedbackModel>().apply {
        value = FeedbackModel()
    }

    val startDocToListEvent = SingleLiveEvent<Unit>()
    val summaryErrorEvent = SingleLiveEvent<String>()
    val descriptionErrorEvent = SingleLiveEvent<String>()
    val setFeedbackImageEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    override fun updateAddressData(
        location: LatLng,
        addressTitle: String,
        addressSnippet: String,
        isSuccess: Boolean
    ) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = addressTitle, snippet = addressSnippet)
            feedbackModel.value!!.address  = addressTitle
            feedbackModel.value!!.location = addressSnippet
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            feedbackModel.value!!.address = addressTitle
            feedbackModel.value!!.location = "다른 지역을 선택해주세요."
        }
    }

    fun clickSetImage() {
        setFeedbackImageEvent.call()
    }

    fun clickPostFeedback() {
        createFeedbackUseCase.execute(feedbackMapper.mapFrom(feedbackModel.value!!), object: DisposableSubscriber<Pair<FeedbackEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<FeedbackEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) createSuccess(feedbackMapper.mapEntityToModel(t.first))
                else createFail(t.second.message, feedbackMapper.mapEntityToModel(t.first))
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }

    private fun createSuccess(feedbackModel: FeedbackModel) {
        this.feedbackModel.value = feedbackModel
        startDocToListEvent.call()
    }

    private fun createFail(message: String, feedbackModel: FeedbackModel) {
        createToastEvent.value = message
        this.feedbackModel.value = feedbackModel
    }
}
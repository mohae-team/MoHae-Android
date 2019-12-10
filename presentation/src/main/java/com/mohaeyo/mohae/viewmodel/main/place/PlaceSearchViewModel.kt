package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.usecase.DisLikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.LikePlaceInfoUseCase
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.PlaceMapper
import com.mohaeyo.mohae.model.MapMakerModel
import com.mohaeyo.mohae.model.PlaceModel
import io.reactivex.subscribers.DisposableSubscriber

class PlaceSearchViewModel(
    private val getPlaceInfoUseCase: GetPlaceInfoUseCase,
    private val likePlaceInfoUseCase: LikePlaceInfoUseCase,
    private val disLikePlaceInfoUseCase: DisLikePlaceInfoUseCase,
    private val placeMapper: PlaceMapper
): BaseLocationViewModel() {
    val placeModel = MutableLiveData<PlaceModel>().apply {
        value = PlaceModel(
            name = "지역을 선택해주세요",
            likeCount = 0,
            description = "지역을 선택해주세요.",
            isLike = false,
            location = "지역을 선택해주세요"
        )
    }

    val startSearchToDocEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    override fun updateAddressData(location: LatLng, addressTitle: String, addressSnippet: String, isSuccess: Boolean) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = addressTitle, snippet = addressSnippet)
            placeModel.value!!.location = addressSnippet
            getPlaceInfo(addressSnippet)
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            placeModel.value = PlaceModel(
                name = "다른 지역을 선택해주세요.",
                likeCount = 0,
                description = "다른 지역을 선택해주세요.",
                isLike = false
            )
        }
    }

    fun clickSearchToDoc() {
        startSearchToDocEvent.call()
    }

    fun clickLike() {
        if (placeModel.value!!.isLike) disLikePlaceInfo()
        else likePlaceInfo()
    }

    fun getPlaceInfo(location: String)
            = getPlaceInfoUseCase.execute(location, object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
        override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
            if (t.second.isSuccess) getSuccess(placeMapper.mapFrom(t.first))
            else getFail(t.second.message)
        }

        override fun onComplete() {

        }

        override fun onError(t: Throwable){
            createToastEvent.value = "알 수 없는 오류가 발생했습니다"
        }
    })

    fun getSuccess(place: PlaceModel) {
        placeModel.value = place
    }

    fun getFail(message: String) {
        placeModel.value = PlaceModel(
            name = message,
            description = message,
            isLike = false,
            likeCount = 0
        )

        createToastEvent.value = message
    }

    fun likePlaceInfo() {
        likePlaceInfoUseCase.execute(placeModel.value!!.location, object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) likeSuccess()
                else likeFail(t.second.message)
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun likeSuccess() {
        with(placeModel.value!!) {
            this.isLike = !isLike
            this.likeCount = likeCount.plus(1)

            placeModel.value = this
        }

        createToastEvent.value = "좋아요가 반영되었습니다"
    }

    fun likeFail(message: String) {
        createToastEvent.value = message
    }

    fun disLikePlaceInfo() {
        disLikePlaceInfoUseCase.execute(placeModel.value!!.location, object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) disLikeSuccess()
                else disLikeFail(t.second.message)
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun disLikeSuccess() {
        with(placeModel.value!!) {
            this.isLike = !isLike
            this.likeCount = likeCount.minus(1)

            placeModel.value = this
        }

        createToastEvent.value = "좋아요가 취소되었습니다"
    }

    fun disLikeFail(message: String) {
        createToastEvent.value = message
    }
}
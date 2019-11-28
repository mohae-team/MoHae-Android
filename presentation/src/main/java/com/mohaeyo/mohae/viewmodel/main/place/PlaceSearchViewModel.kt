package com.mohaeyo.mohae.viewmodel.main.place

import android.util.Log
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

    val placeName = MutableLiveData<String>().apply { value = "지역을 선택해주세요." }
    val placeLikeCount = MutableLiveData<Int>().apply { value = 0 }
    val placeDescription = MutableLiveData<String>().apply { value = "지역을 선택해주세요." }
    val placeIsLike = MutableLiveData<Boolean>().apply { value = false }
    val placeLocation = MutableLiveData<String>().apply { value = "지역을 선택해주세요." }

    val startSearchToDocEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

    }

    override fun updateAddressData(location: LatLng, addressTitle: String, addressSnippet: String, isSuccess: Boolean) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = addressTitle, snippet = addressSnippet)
            placeLocation.value = addressSnippet
            getPlaceInfo(addressSnippet)
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            placeName.value = "다른 지역을 선택해주세요."
            placeLikeCount.value = 0
            placeDescription.value = "다른 지역을 선택해주세요."
            placeIsLike.value = false
        }
    }

    fun clickSearchToDoc() {
        startSearchToDocEvent.call()
    }

    fun clickLike() {
        if (placeIsLike.value!!) disLikePlaceInfo()
        else likePlaceInfo()
    }

    private fun getPlaceInfo(location: String)
            = getPlaceInfoUseCase.execute(location, object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
        override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
            if (t.second.isSuccess) getSuccess(placeMapper.mapFrom(t.first))
            else getFail(t.second.message)
        }

        override fun onComplete() {

        }

        override fun onError(t: Throwable?){
            Log.e("asdasd", t.toString())
        }
    })

    private fun getSuccess(place: PlaceModel) {
        placeName.value = place.name
        placeDescription.value = place.description
        placeIsLike.value = place.isLike
        placeLikeCount.value = place.likeCount
    }

    private fun getFail(message: String) {
        placeName.value = message
        placeDescription.value = message
        placeIsLike.value = false
        placeLikeCount.value = 0

        createToastEvent.value = message
    }

    private fun likePlaceInfo() {
        likePlaceInfoUseCase.execute(placeLocation.value!!, object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) likeSuccess()
                else likeFail(t.second.message)
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    private fun likeSuccess() {
        placeLikeCount.value = placeLikeCount.value!!.plus(1)
        placeIsLike.value = !(placeIsLike.value!!)
    }

    private fun likeFail(message: String) {
        createToastEvent.value = message
    }

    private fun disLikePlaceInfo() {
        disLikePlaceInfoUseCase.execute(placeLocation.value!!, object: DisposableSubscriber<Pair<PlaceEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<PlaceEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) disLikeSuccess()
                else disLikeFail(t.second.message)
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    private fun disLikeSuccess() {
        placeLikeCount.value = placeLikeCount.value!!.minus(1)
        placeIsLike.value = !(placeIsLike.value!!)
    }

    private fun disLikeFail(message: String) {
        createToastEvent.value = message
    }
}
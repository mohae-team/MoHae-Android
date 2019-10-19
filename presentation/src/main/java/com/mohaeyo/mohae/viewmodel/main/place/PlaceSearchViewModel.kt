package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.MapMakerModel

class PlaceSearchViewModel(): BaseLocationViewModel() {
    val placeName = MutableLiveData<String>().apply { value = "지역을 선택해주세요." }
    val placeLikeCount = MutableLiveData<Int>().apply { value = 0 }
    val placeDescription = MutableLiveData<String>().apply { value = "지역을 선택해주세요." }
    val placeIsLike = MutableLiveData<Boolean>().apply { value = false }

    val startSearchToDocEvent = SingleLiveEvent<Unit>()
    val likeEvent = SingleLiveEvent<Unit>()
    val dislikeEvent = SingleLiveEvent<Unit>()

    override fun updateAddressData(location: LatLng, addressTitle: String, addressSnippet: String, isSuccess: Boolean) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = addressTitle, snippet = addressSnippet)
            placeName.value = "아직 정보가 없습니다."
            placeLikeCount.value = 0
            placeDescription.value = "아직 정보가 없습니다."
            placeIsLike.value = false
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            placeName.value = "다른 지역을 선택해주세요."
            placeLikeCount.value = 0
            placeDescription.value = "다른 지역을 선택해주세요."
            placeIsLike.value = false
        }
        checkLike()
    }

    private fun checkLike() {
        if (placeIsLike.value!!) {
            likeEvent.call()
            return
        }
        dislikeEvent.call()
    }

    fun clickSearchToDoc() {
        startSearchToDocEvent.call()
    }

    fun clickLike() {
        val isLike = placeIsLike.value!!

        if (isLike) {
            placeLikeCount.value = placeLikeCount.value!!.minus(1)
            dislikeEvent.call()
        }
        else {
            placeLikeCount.value = placeLikeCount.value!!.plus(1)
            likeEvent.call()
        }

        placeIsLike.value = !isLike
    }
}
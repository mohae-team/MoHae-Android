package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.MapMakerModel

class MyPageProfileEditViewModel: BaseLocationViewModel() {

    val startProfileEvent = SingleLiveEvent<Unit>()

    val imageUrlText = MutableLiveData<String>()
    val nameText = MutableLiveData<String>()
    val addressText = MutableLiveData<String>()
    val idText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()

    val getProfileImageEvent = SingleLiveEvent<Unit>()
    val descriptionErrorEvent = SingleLiveEvent<String>()


    init {
        imageUrlText.value = "네트워크 상태를 확인해주세요."
        nameText.value = "내 정보를 불러올 수 없습니다."
        addressText.value = "네트워크 상태를 확인해주세요."
        idText.value = "네트워크 상태를 확인해주세요."
        descriptionText.value = "네트워크 상태를 확인해주세요."
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
            addressText.value = addressTitle
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            addressText.value = "다른 지역을 선택해주세요."
        }
    }

    fun clickImageEdit() {
        getProfileImageEvent.call()
    }

    fun clickComplete() {
        startProfileEvent.call()
    }
}
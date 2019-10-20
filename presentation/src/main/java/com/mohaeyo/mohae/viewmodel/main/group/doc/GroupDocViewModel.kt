package com.mohaeyo.mohae.viewmodel.main.group.doc

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.MapMakerModel

class GroupDocViewModel: BaseLocationViewModel() {

    val titleText = MutableLiveData<String>()
    val addressText = MutableLiveData<String>()
    val dateText = MutableLiveData<String>()
    val summaryText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()

    val startDocToListEvent = SingleLiveEvent<Unit>()
    val titleErrorEvent = SingleLiveEvent<String>()
    val dateErrorEvent = SingleLiveEvent<String>()
    val summaryErrorEvent = SingleLiveEvent<String>()
    val descriptionErrorEvent = SingleLiveEvent<String>()

    override fun updateAddressData(
        location: LatLng,
        addressTitle: String,
        addressSnippet: String,
        isSuccess: Boolean
    ) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = addressTitle, snippet = addressSnippet)
            addressText.value = addressSnippet
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            addressText.value = "다른 지역을 선택해주세요."
        }
    }

    fun clickPostGroup() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
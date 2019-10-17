package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.MapMakerModel

class PlaceDocViewModel(): BaseLocationViewModel() {

    val placeName = MutableLiveData<String>()
    val placeDescription = MutableLiveData<String>()
    val placeLocation = MutableLiveData<String>()

    override fun updateAddressData(location: LatLng, addressTitle: String, addressSnippet: String, isSuccess: Boolean) {
        drawMarkerEvent.value =
            MapMakerModel(title = addressTitle, snippet = addressSnippet, location = location)
        placeLocation.value = addressSnippet
    }

    val startDocToListEvent = SingleLiveEvent<Unit>()

    fun clickPostPlace() {
        startDocToListEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }
}
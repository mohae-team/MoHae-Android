package com.mohaeyo.mohae.viewmodel.main.place

import android.location.Geocoder
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.MapMakerModel
import java.io.IOException
import kotlin.IllegalStateException

class PlaceSearchViewModel(): BaseViewModel() {
    val placeName = MutableLiveData<String>().apply { value = "지역을 선택해주세요." }
    val placeLikeCount = MutableLiveData<Int>().apply { value = 0 }
    val placeDescription = MutableLiveData<String>().apply { value = "지역을 선택해주세요." }
    val placeIsLike = MutableLiveData<Boolean>().apply { value = false }

    val createToastEvent = SingleLiveEvent<String>()
    val drawMarkerEvent = SingleLiveEvent<MapMakerModel>()
    val locationUpdateEvent = SingleLiveEvent<Unit>()
    val startListToDocEvent = SingleLiveEvent<Unit>()
    val likeEvent = SingleLiveEvent<Unit>()
    val dislikeEvent = SingleLiveEvent<Unit>()

    fun initGoogleMapLocation(settingsClient: SettingsClient, locationRequest: LocationRequest){
        val mLocationSettingsRequest = createLocationBuilder(locationRequest).build()
        val locationResponse = settingsClient.checkLocationSettings(mLocationSettingsRequest)

        listenLocationResponse(locationResponse)
    }

    fun findLocation(geocoder: Geocoder, location: LatLng) {
        try {
            val addresses
                    = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            try {
                updateAddressData(location = location, addressTitle = addresses[0].locality,
                    addressSnippet = addresses[0].getAddressLine(0), likeCount = 0,
                    description = "아직 정보가 없습니다.", isLike = false)
            } catch (exception: Exception) {
                when(exception) {
                    is IllegalStateException, is IndexOutOfBoundsException -> {
                        updateAddressData(location = location, addressTitle = "다른 지역을 선택해주세요.",
                            addressSnippet = "다른 지역을 선택해주세요.", likeCount = 0,
                            description = "다른 지역을 선택해주세요.", isLike = false)
                    }
                    else -> throw exception
                }
            }
        } catch (exception: IOException) {
            createToastEvent.value = "네트워크 연결을 확인해주세요."
        }
    }

    private fun updateAddressData(location: LatLng, addressTitle: String, addressSnippet: String,
                                  likeCount: Int, description: String, isLike: Boolean) {
        drawMarkerEvent.value =
            MapMakerModel(location = location, title = addressTitle, snippet = addressSnippet)
        placeName.value = addressSnippet
        placeLikeCount.value = likeCount
        placeDescription.value = description
        placeIsLike.value = isLike
        checkLike()
    }

    private fun checkLike() {
        if (placeIsLike.value!!) {
            likeEvent.call()
            return
        }
        dislikeEvent.call()
    }

    private fun listenLocationResponse(locationResponse: Task<LocationSettingsResponse>) {
        with(locationResponse){
            addOnSuccessListener{
                locationUpdateEvent.call()
            }
            addOnFailureListener{
                when ((it as ApiException).statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> createToastEvent.value = "위치환경을 확인해주세요."
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> createToastEvent.value = "위치환경 설정을 확인해주세요."
                }
            }
        }
    }

    private fun createLocationBuilder(locationRequest: LocationRequest): LocationSettingsRequest.Builder {
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        return builder
    }

    fun clickListToDoc() {
        startListToDocEvent.call()
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
package com.mohaeyo.mohae.base

import android.location.Geocoder
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.mohaeyo.mohae.model.MapMakerModel
import java.io.IOException

abstract class BaseLocationViewModel : BaseViewModel() {

    val drawMarkerEvent = SingleLiveEvent<MapMakerModel>()
    val locationUpdateEvent = SingleLiveEvent<Unit>()
    val createToastEvent = SingleLiveEvent<String>()

    abstract fun updateAddressData(location: LatLng, addressTitle: String, addressSnippet: String, isSuccess: Boolean)

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
                    addressSnippet = addresses[0].getAddressLine(0), isSuccess = true)
            } catch (exception: Exception) {
                when(exception) {
                    is IllegalStateException, is IndexOutOfBoundsException -> {
                        updateAddressData(location = location, addressTitle = "다른 지역을 선택해주세요.",
                            addressSnippet = "다른 지역을 선택해주세요.", isSuccess = true)
                    }
                    else -> throw exception
                }
            }
        } catch (exception: IOException) {
            createToastEvent.value = "네트워크 연결을 확인해주세요."
        }
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
}
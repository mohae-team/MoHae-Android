package com.mohaeyo.mohae.model

import com.google.android.gms.maps.model.LatLng

data class MapMakerModel(
    val title: String,
    val snippet: String,
    val location: LatLng
)
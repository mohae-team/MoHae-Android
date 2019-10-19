package com.mohaeyo.mohae.base

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.core.app.ActivityCompat
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mohaeyo.mohae.R
import org.jetbrains.anko.support.v4.toast
import java.util.*

abstract class BaseLocationFragment<V: ViewDataBinding>: DataBindingFragment<V>() {

    abstract val viewModel: BaseLocationViewModel

    abstract val mapId: Int

    lateinit var map: GoogleMap

    val locationRequest by lazy {
        val location = LocationRequest()
        location.interval = 10000
        location.fastestInterval = 10000
        location.numUpdates = 1
        location.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        return@lazy location
    }

    val fusedLocationClient by lazy { LocationServices.getFusedLocationProviderClient(requireActivity()) }
    val settingsClient by lazy { LocationServices.getSettingsClient(requireActivity()) }

    override fun onResume() {
        super.onResume()
        checkPermission()
    }

    override fun onStop() {
        super.onStop()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            500 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    checkPermission()
                else actNotAllow()
            }
            else -> actNotAllow()
        }
    }

    fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 500)
        } else {
            initLocation()
        }
    }

    fun initLocation() {
        setMapFragment(childFragmentManager.findFragmentById(mapId) as SupportMapFragment)
        viewModel.initGoogleMapLocation(settingsClient, locationRequest)
    }

    fun setMapFragment(fragment: SupportMapFragment) {
        fragment.getMapAsync {
            map = it
            map.uiSettings.isZoomControlsEnabled = true
            try { map.isMyLocationEnabled = true }
            catch (exception: SecurityException) { checkPermission() }

            map.setOnMapClickListener { location ->
                map.clear()
                viewModel.findLocation(Geocoder(context, Locale.KOREA), location)
            }
        }
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result)
            val location = result!!.locations[0]
            viewModel.findLocation(Geocoder(context, Locale.KOREA), LatLng(location.latitude, location.longitude))
        }
    }

    fun actNotAllow() {
        requireActivity().supportFragmentManager.primaryNavigationFragment!!
            .findNavController().navigate(R.id.action_mainFragment_self)
        toast("위치정보사용을 허락하지 않아 시설을 중지합니다.")
    }

    fun drawMarker(location: LatLng, title: String?, snippet: String?) {
        val options = MarkerOptions()

        options.position(location)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            .title(title)
            .snippet(snippet)

        val marker = map.addMarker(options)

        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(marker.position, 16f)
        )
    }
}
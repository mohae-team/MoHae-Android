package com.mohaeyo.mohae.ui.fragment.main.place

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.*
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentPlaceSearchBinding
import com.mohaeyo.mohae.doBackAnimation
import com.mohaeyo.mohae.doCommonAnimation
import com.mohaeyo.mohae.viewmodel.main.place.PlaceSearchViewModel
import com.mohaeyo.mohae.viewmodel.main.place.PlaceSearchViewModelFactory
import kotlinx.android.synthetic.main.fragment_place_search.*
import javax.inject.Inject
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.CameraUpdateFactory
import android.location.Geocoder
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import org.jetbrains.anko.support.v4.toast
import java.util.*

class PlaceSearchFragment: EndPointDataBindingFragment<FragmentPlaceSearchBinding>() {

    @Inject
    lateinit var factory: PlaceSearchViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(PlaceSearchViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_place_search

    private lateinit var map: GoogleMap

    private val locationRequest by lazy {
        val location = LocationRequest()
        location.interval = 10000
        location.fastestInterval = 10000
        location.numUpdates = 1
        location.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        return@lazy location
    }

    private val fusedLocationClient by lazy { LocationServices.getFusedLocationProviderClient(requireActivity()) }
    private val settingsClient by lazy { LocationServices.getSettingsClient(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()
        binding.vm = viewModel

    }

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
                    initLocation()
                else {
                    parentFragment!!.findNavController().navigate(R.id.action_placeFragment_to_groupFragment)
                    toast("위치정보사용을 허락하지 않아 시설을 중지합니다.")
                }
            }
            else -> {
                parentFragment!!.findNavController().navigate(R.id.action_placeFragment_to_groupFragment)
                toast("위치정보사용을 허락하지 않아 시설을 중지합니다.")
            }
        }
    }

    private fun observeEvent() {
        viewModel.startListToDocEvent.observe(this, Observer {
            place_search_add_fab.doCommonAnimation(R.drawable.add_to_check)
            place_search_back_fab.doBackAnimation(true)
            findNavController().navigate(R.id.action_placeSearchFragment_to_placeDocFragment)
        })

        viewModel.likeEvent.observe(this, Observer {
            place_search_name_card_like_check.setBackgroundResource(R.drawable.ic_favorite_pink)
        })

        viewModel.dislikeEvent.observe(this, Observer {
            place_search_name_card_like_check.setBackgroundResource(R.drawable.ic_favorite_border_black)
        })

        viewModel.drawMarkerEvent.observe(this, Observer {
            drawMarker(title = it.title, snippet = it.snippet, location = it.location)
        })

        viewModel.locationUpdateEvent.observe(this, Observer {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        })

        viewModel.createToastEvent.observe(this, Observer { toast(it) })
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 500)
        } else {
            initLocation()
        }
    }

    private fun initLocation() {
        setMapFragment(childFragmentManager.findFragmentById(R.id.place_search_map) as SupportMapFragment)
        viewModel.initGoogleMapLocation(settingsClient, locationRequest)
    }

    private fun setMapFragment(fragment: SupportMapFragment) {
        fragment.getMapAsync {
            map = it
            map.uiSettings.isZoomControlsEnabled = true
            map.isMyLocationEnabled = true

            map.setOnMapClickListener { location ->
                map.clear()
                viewModel.findLocation(Geocoder(context, Locale.KOREA), location)
            }
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result)
            val location = result!!.locations[0]
            viewModel.findLocation(Geocoder(context, Locale.KOREA), LatLng(location.latitude, location.longitude))
        }
    }

    private fun drawMarker(location: LatLng, title: String?, snippet: String?) {
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
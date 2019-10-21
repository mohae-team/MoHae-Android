package com.mohaeyo.mohae.ui.fragment.main.mypage

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.BaseLocationFragment
import com.mohaeyo.mohae.databinding.FragmentMypageProfileEditBinding
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModelFactory
import kotlinx.android.synthetic.main.fragment_mypage_profile_edit.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class MyPageProfileEditFragment: BaseLocationFragment<FragmentMypageProfileEditBinding>() {

    override val mapId: Int
        get() = R.id.mypage_profile_edit_search_map

    @Inject
    lateinit var factory: MyPageProfileEditViewModelFactory

    override val viewModel
            by lazy { ViewModelProviders.of(this, factory).get(MyPageProfileEditViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_mypage_profile_edit

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = backToProfile()
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()
        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startProfileEvent.observe(this, Observer { backToProfile() })

        viewModel.imageUrlText.observe(this, Observer { setProfileImage() })

        viewModel.drawMarkerEvent.observe(this, Observer {
            drawMarker(title = it.title, snippet = it.snippet, location = it.location)
        })

        viewModel.locationUpdateEvent.observe(this, Observer {
            try { fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper()) }
            catch (exception: SecurityException) { checkPermission() }
        })

        viewModel.createToastEvent.observe(this, Observer { toast(it) })

        viewModel.descriptionErrorEvent.observe(this, Observer { mypage_profile_description_card_edit_lay.error = it })
    }

    private fun backToProfile()
            = findNavController().navigate(R.id.action_myPageProfileEditFragment_to_myPageProfileFragment)

    private fun setProfileImage() {

    }
}
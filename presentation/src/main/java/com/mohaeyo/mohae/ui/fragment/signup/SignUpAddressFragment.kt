package com.mohaeyo.mohae.ui.fragment.signup

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
import com.mohaeyo.mohae.databinding.FragmentSignupAddressBinding
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModel
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModelFactory
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


class SignUpAddressFragment: BaseLocationFragment<FragmentSignupAddressBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_signup_address

    @Inject
    lateinit var factory: SignUpViewModelFactory

    override val viewModel by lazy { ViewModelProviders.of(this, factory).get(SignUpViewModel::class.java) }

    override val mapId: Int
        get() = R.id.signup_address_search_map

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_signUpAddressFragment_to_signUpFragment)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel


        getArgSignUpData()
        observeViewModelEvent()
    }

    private fun observeViewModelEvent() {
        viewModel.startSignInEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_signUpAddressFragment_to_loginFragment)
        })

        viewModel.startSignUpEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_signUpAddressFragment_to_signUpFragment)
        })

        viewModel.drawMarkerEvent.observe(this, Observer {
            drawMarker(title = it.title, snippet = it.snippet, location = it.location)
        })

        viewModel.locationUpdateEvent.observe(this, Observer {
            try { fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper()) }
            catch (exception: SecurityException) { checkPermission() }
        })

        viewModel.createToastEvent.observe(this, Observer { toast(it) })
    }

    private fun getArgSignUpData() {
        viewModel.usernameText.value = arguments!!.getString("username")
        viewModel.idText.value = arguments!!.getString("id")
        viewModel.passwordText.value = arguments!!.getString("password")
    }
}
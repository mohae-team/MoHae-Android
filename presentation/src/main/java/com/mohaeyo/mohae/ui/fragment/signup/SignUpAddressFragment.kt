package com.mohaeyo.mohae.ui.fragment.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.BaseLocationFragment
import com.mohaeyo.mohae.databinding.FragmentSignupAddressBinding
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModel
import javax.inject.Inject


class SignUpAddressFragment: BaseLocationFragment<FragmentSignupAddressBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_signup_address

    @Inject
    override lateinit var viewModel: SignUpViewModel

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
    }

    override fun observeEvent() {
        viewModel.startSignInEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_signUpAddressFragment_to_loginFragment)
        })

        viewModel.startSignUpEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_signUpAddressFragment_to_signUpFragment)
        })
    }

    private fun getArgSignUpData() {
        viewModel.usernameText.value = arguments!!.getString("username")
        viewModel.idText.value = arguments!!.getString("id")
        viewModel.passwordText.value = arguments!!.getString("password")
    }
}
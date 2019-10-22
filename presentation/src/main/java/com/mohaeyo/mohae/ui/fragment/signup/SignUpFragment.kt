package com.mohaeyo.mohae.ui.fragment.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentSignupBinding
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModel
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModelFactory
import kotlinx.android.synthetic.main.fragment_signup.*
import javax.inject.Inject


class SignUpFragment: DataBindingFragment<FragmentSignupBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_signup

    @Inject
    lateinit var factory: SignUpViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(SignUpViewModel::class.java) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().onBackPressedDispatcher.addCallback(
            this, object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        observeViewModelEvent()
    }

    private fun observeViewModelEvent() {
        viewModel.startSignUpAddressEvent.observe(this, Observer {
            val bundle = Bundle()
            bundle.putString("username", viewModel.usernameText.value)
            bundle.putString("id", viewModel.idText.value)
            bundle.putString("password", viewModel.passwordText.value)
            findNavController().navigate(R.id.action_signUpFragment_to_signUpAddressFragment, bundle)
        })

        viewModel.startSignInEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        })

        viewModel.usernameErrorEvent.observe(this, Observer { signup_username_edit_lay.error = it })

        viewModel.idErrorEvent.observe(this, Observer { signup_id_edit_lay.error = it })

        viewModel.passwordErrorEvent.observe(this, Observer { signup_password_edit_lay.error =  it})

        viewModel.passwordCheckErrorEvent.observe(this, Observer { signup_password_check_edit_lay.error =  it})
    }
}
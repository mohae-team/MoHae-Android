package com.mohaeyo.mohae.ui.fragment.signup

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentSignupBinding
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModel
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModelFactory
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
                    findNavController(view!!).navigate(R.id.action_signUpFragment_to_loginFragment)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        observeViewModelEvent()
    }

    private fun observeViewModelEvent() {
        viewModel.startSignInEvent.observe(this, Observer {
            findNavController(view!!).navigate(R.id.action_signUpFragment_to_loginFragment)
        })
    }
}
package com.mohaeyo.mohae.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentLoginBinding
import com.mohaeyo.mohae.viewmodel.login.LoginViewModel
import com.mohaeyo.mohae.viewmodel.login.LoginViewModelFactory
import javax.inject.Inject

class LoginFragment: DataBindingFragment<FragmentLoginBinding>() {

    @Inject
    lateinit var factory: LoginViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(LoginViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_login


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        observeViewModelEvent()
    }

    private fun observeViewModelEvent() {
        viewModel.startMainEvent.observe(this, Observer {
            findNavController(view!!).navigate(R.id.action_loginFragment_to_main_fragment)
        })

        viewModel.startSignUpEvent.observe(this, Observer {
            findNavController(view!!).navigate(R.id.action_loginFragment_to_signUpFragment)
        })
    }
}
package com.mohaeyo.mohae.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentLoginBinding
import com.mohaeyo.mohae.viewmodel.LoginViewModel
import com.mohaeyo.mohae.viewmodel.facotry.LoginViewModelFactory
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

        viewModel.startMainEvent.observe(this, Observer {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_main_fragment2)
        })
    }
}
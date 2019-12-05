package com.mohaeyo.mohae.ui.fragment.signin

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentSigninBinding
import com.mohaeyo.mohae.viewmodel.signin.SignInViewModel
import kotlinx.android.synthetic.main.fragment_signin.*
import javax.inject.Inject

class SignInFragment: EndPointDataBindingFragment<FragmentSigninBinding>() {
    @Inject
    override lateinit var viewModel: SignInViewModel

    override val layoutId: Int
        get() = R.layout.fragment_signin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeEvent() {
        viewModel.startMainEvent.observe(this, Observer {
            findNavController(view!!).navigate(R.id.action_loginFragment_to_main_fragment)
        })

        viewModel.startSignUpEvent.observe(this, Observer {
            findNavController(view!!).navigate(R.id.action_loginFragment_to_signUpFragment)
        })

        viewModel.idErrorEvent.observe(this, Observer { login_id_edit_lay.error = it })

        viewModel.passwordErrorEvent.observe(this, Observer { login_password_edit_lay.error =  it})
    }
}
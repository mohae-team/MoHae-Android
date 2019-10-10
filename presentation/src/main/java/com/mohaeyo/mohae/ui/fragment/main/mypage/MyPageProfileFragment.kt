package com.mohaeyo.mohae.ui.fragment.main.mypage

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.BR
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentMypageProfileBinding
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModelFactory
import javax.inject.Inject

class MyPageProfileFragment: EndPointDataBindingFragment<FragmentMypageProfileBinding>() {

    @Inject
    lateinit var factory: MyPageProfileViewModelFactory

    private val viewModel
            by lazy { ViewModelProviders.of(this, factory).get(MyPageProfileViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_mypage_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()
        binding.vm = viewModel
    }

    private fun observeEvent() {
        viewModel.startProfileData.observe(this, Observer {
            binding.setVariable(BR.profileModel, it)
        })

        viewModel.startProfileEditEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_myPageProfileFragment_to_myPageProfileEditFragment)
        })

        viewModel.startLoginEvent.observe(this, Observer {
            requireActivity().supportFragmentManager.primaryNavigationFragment!!.
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        })
    }


}
package com.mohaeyo.mohae.ui.fragment.main.mypage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentMypageProfileBinding
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModel
import javax.inject.Inject

class MyPageProfileFragment: EndPointDataBindingFragment<FragmentMypageProfileBinding>() {


    @Inject
    override lateinit var viewModel: MyPageProfileViewModel

    override val layoutId: Int
        get() = R.layout.fragment_mypage_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeEvent() {

        viewModel.startProfileEditEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_myPageProfileFragment_to_myPageProfileEditFragment)
        })

        viewModel.startLoginEvent.observe(this, Observer {
            requireActivity().supportFragmentManager.primaryNavigationFragment!!.
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        })


    }


}
package com.mohaeyo.mohae.ui.fragment.main.mypage

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.BR
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentMypageProfileEditBinding
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModelFactory
import javax.inject.Inject

class MyPageProfileEditFragment: EndPointDataBindingFragment<FragmentMypageProfileEditBinding>() {

    @Inject
    lateinit var factory: MyPageProfileEditViewModelFactory

    private val viewModel
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

        viewModel.imageUrlData.observe(this, Observer {
            setProfileImage()
        })
    }

    private fun backToProfile()
            = findNavController().navigate(R.id.action_myPageProfileEditFragment_to_myPageProfileFragment)

    private fun setProfileImage() {

    }
}
package com.mohaeyo.mohae.ui.fragment.main.mypage

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mohaeyo.mohae.BR
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentMypageProfileBinding
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileViewModelFactory
import kotlinx.android.synthetic.main.fragment_mypage_profile.*
import java.net.URI
import javax.inject.Inject

class MyPageProfileFragment: EndPointDataBindingFragment<FragmentMypageProfileBinding>() {

    @Inject
    lateinit var factory: MyPageProfileViewModelFactory

    override val viewModel
            by lazy { ViewModelProviders.of(this, factory).get(MyPageProfileViewModel::class.java) }

    override val layoutId: Int
        get() = R.layout.fragment_mypage_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeEvent() {
        viewModel.userModel.observe(this, Observer {
            Glide.with(mypage_profile_imv)
                .load(it.imageFile.toString())
                .apply(RequestOptions.circleCropTransform())
                .into(mypage_profile_imv)
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
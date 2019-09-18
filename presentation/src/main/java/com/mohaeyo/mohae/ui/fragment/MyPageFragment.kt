package com.mohaeyo.mohae.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentMypageBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class MyPageFragment: DataBindingFragment<FragmentMypageBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_mypage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
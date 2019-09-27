package com.mohaeyo.mohae.ui.fragment.main.mypage

import android.os.Bundle
import android.view.View
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointFragment
import com.mohaeyo.mohae.databinding.FragmentMypageBinding

class MyPageFragment: EndPointFragment<FragmentMypageBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_mypage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
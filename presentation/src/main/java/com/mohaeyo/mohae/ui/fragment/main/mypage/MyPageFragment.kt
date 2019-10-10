package com.mohaeyo.mohae.ui.fragment.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointFragment

class MyPageFragment: EndPointFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }
}
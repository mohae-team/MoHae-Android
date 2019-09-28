package com.mohaeyo.mohae.ui.fragment.main.qa

import android.os.Bundle
import android.view.View
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.EndPointDataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentQaBinding

class QAFragment: EndPointDataBindingFragment<FragmentQaBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_qa

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}
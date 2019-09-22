package com.mohaeyo.mohae.ui.fragment.main.qa

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentQaBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class QAFragment: DataBindingFragment<FragmentQaBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_qa

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}
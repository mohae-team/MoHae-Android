package com.mohaeyo.mohae.ui.fragment.main.place

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.databinding.FragmentPlaceBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class PlaceFragment: DataBindingFragment<FragmentPlaceBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_place

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
package com.mohaeyo.mohae.ui.fragment.main.place

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.mohaeyo.mohae.R
import com.mohaeyo.mohae.backButtonSubject
import com.mohaeyo.mohae.base.DataBindingFragment
import com.mohaeyo.mohae.base.EndPointFragment
import com.mohaeyo.mohae.databinding.FragmentPlaceBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class PlaceFragment: EndPointFragment<FragmentPlaceBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_place

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
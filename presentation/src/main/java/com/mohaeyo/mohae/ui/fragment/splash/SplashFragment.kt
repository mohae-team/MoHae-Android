package com.mohaeyo.mohae.ui.fragment.splash

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_splash, container, false)


    override fun onStart() {
        super.onStart()
        (splash.drawable as Animatable).start()

        Handler().postDelayed({
            startApp()
        },1500)
    }

    private fun startApp() {
        context?.let {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}
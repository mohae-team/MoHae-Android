package com.mohaeyo.mohae.ui.fragment.splash

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mohaeyo.mohae.R
import kotlinx.android.synthetic.main.fragment_splash.*
import org.jetbrains.anko.support.v4.toast



class SplashFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_splash, container, false)


    override fun onStart() {
        super.onStart()
        (splash.drawable as Animatable).start()

        Handler().postDelayed({
            if (ActivityCompat.checkSelfPermission(context!!,
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 500)
            } else {
                startApp()
            }
        },1500)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            500 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startApp()
                else {
                    activity!!.finish()
                    toast("위치정보사용을 허락 하지않아 앱을 중지합니다.")
                }
            }
            else -> {
                activity!!.finish()
                toast("위치정보사용을 허락 하지않아 앱을 중지합니다.")
            }
        }
    }

    private fun startApp() {
        context?.let {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}
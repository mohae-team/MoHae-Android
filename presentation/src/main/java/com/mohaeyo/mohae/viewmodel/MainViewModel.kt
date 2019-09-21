package com.mohaeyo.mohae.viewmodel

import androidx.lifecycle.Lifecycle
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback

class MainViewModel: BaseViewModel(), LifecycleCallback {
    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> {

            }

            Lifecycle.Event.ON_STOP -> {

            }
        }
    }
}
package com.mohaeyo.mohae.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(), LifecycleCallback {
    val createToastEvent = SingleLiveEvent<String>()
}
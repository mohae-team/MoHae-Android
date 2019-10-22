package com.mohaeyo.mohae.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    val createToastEvent = SingleLiveEvent<String>()
}
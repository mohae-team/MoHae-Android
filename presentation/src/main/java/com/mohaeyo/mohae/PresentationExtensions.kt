package com.mohaeyo.mohae

import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject


val backButtonSubject: Subject<Long> =
    BehaviorSubject.createDefault(0L)
        .toSerialized()

fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()
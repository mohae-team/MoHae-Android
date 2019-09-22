package com.mohaeyo.mohae

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()
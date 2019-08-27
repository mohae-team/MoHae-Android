package com.mohaeyo.mohae.base

import androidx.lifecycle.Lifecycle

interface LifecycleCallback {
    fun apply(event: Lifecycle.Event)
}
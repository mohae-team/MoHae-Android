package com.mohaeyo.mohae

import android.graphics.drawable.Animatable
import android.view.animation.TranslateAnimation
import androidx.lifecycle.MutableLiveData
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun FloatingActionButton.doCommonAnimation(resId: Int) {
    val avd = AnimatedVectorDrawableCompat.create(context!!, resId)
    this.setImageDrawable(avd)
    (this.drawable as Animatable).start()
}

fun FloatingActionButton.doBackAnimation(isBack: Boolean) {
    val anim =
        if (isBack) TranslateAnimation(0f, -1000f, 0f, 0f)
        else TranslateAnimation(0f, 1000f, 0f, 0f)
    anim.duration = 400
    this.startAnimation(anim)
}

fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()
package com.mohaeyo.mohae

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.drawable.Animatable
import android.net.Uri
import android.view.animation.TranslateAnimation
import androidx.lifecycle.MutableLiveData
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.provider.MediaStore
import android.util.Log
import android.content.ContentValues
import android.provider.DocumentsContract
import android.content.ContentUris
import android.database.Cursor
import android.os.Environment.getExternalStorageDirectory
import android.os.Build
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.provider.MediaStore.MediaColumns
import okhttp3.internal.toLongOrDefault
import java.lang.NumberFormatException


fun FloatingActionButton.doCommonAnimation(resId: Int) {
    val avd = AnimatedVectorDrawableCompat.create(context!!, resId)
    this.setImageDrawable(avd)
    (this.drawable as Animatable).start()
}

fun FloatingActionButton.doBackAnimation(isBack: Boolean) {
    val anim =
        if (isBack) TranslateAnimation(0f, -700f, 0f, 0f)
        else TranslateAnimation(0f, 700f, 0f, 0f)
    anim.duration = 400
    this.startAnimation(anim)
}

fun MutableLiveData<String>.isNotValueBlank() = !this.isValueBlank()

fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()
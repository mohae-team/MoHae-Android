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
        if (isBack) TranslateAnimation(0f, -1000f, 0f, 0f)
        else TranslateAnimation(0f, 1000f, 0f, 0f)
    anim.duration = 400
    this.startAnimation(anim)
}

fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

fun getPath(context: Context, uri: Uri): String? {
    if (DocumentsContract.isDocumentUri(context, uri)) {
        val docId = DocumentsContract.getDocumentId(uri)
        val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val type = split[0]

        if (isExternalStorageDocument(uri)) {
            if ("primary".equals(type, ignoreCase = true)) {
                return context.getExternalFilesDir(null)!!.absolutePath + "/" + split[1]
            }

        } else if (isDownloadsDocument(uri)) {
            var contentUri: Uri

            try {
                contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), docId.toLong())
            } catch (exception: NumberFormatException) {
                contentUri = uri
            }

            return getDataColumn(context, contentUri, null, null)
        } else if (isMediaDocument(uri)) {
            var contentUri: Uri? = null

            when (type) {
                "image" -> contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                "video" -> contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                "audio" -> contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }

            val selection = "_id=?"
            val selectionArgs = arrayOf(split[1])

            return getDataColumn(context, contentUri, selection, selectionArgs)
        }
    } else if ("content".equals(uri.scheme!!, ignoreCase = true))
        return getDataColumn(context, uri, null, null)

    else if ("file".equals(uri.scheme!!, ignoreCase = true))
        return uri.path

    return null
}

fun getDataColumn(context: Context, uri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {

    var cursor: Cursor? = null
    val column = "_data"
    val projection = arrayOf(column)

    try {
        cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
        if (cursor != null && cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndexOrThrow(column)
            return cursor.getString(columnIndex)
        }
    } finally {
        cursor?.close()
    }
    return null
}

fun isExternalStorageDocument(uri: Uri): Boolean
        = "com.android.externalstorage.documents" == uri.authority

fun isDownloadsDocument(uri: Uri): Boolean
        = "com.android.providers.downloads.documents" == uri.authority

fun isMediaDocument(uri: Uri): Boolean
        = "com.android.providers.media.documents" == uri.authority
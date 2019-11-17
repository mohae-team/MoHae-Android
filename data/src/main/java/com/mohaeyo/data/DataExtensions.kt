package com.mohaeyo.data

import android.content.Context
import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.*
import okhttp3.RequestBody.Companion.asRequestBody

fun File.toImageRequestBody(): RequestBody
        = this.asRequestBody(MultipartBody.FORM)

fun String.toFile(): File = File(this)

fun copyStreamToFile(context: Context, uri: Uri): File {
    val file = File(
        "${context.filesDir.path}/${uri.path.hashCode()}.png")
    context.contentResolver.openInputStream(uri).use { input ->
        val outputStream = FileOutputStream(file)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024)
            while (true) {
                val byteCount = input!!.read(buffer)
                if (byteCount < 0) break
                output.write(buffer, 0, byteCount)
            }
            output.flush()
        }
    }
    return file
}
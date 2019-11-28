package com.mohaeyo.data.remote

import android.util.Log
import com.mohaeyo.data.local.pref.LocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(val local: LocalStorage): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", local.getToken(true))
            .build()

        return chain.proceed(request)
    }
}
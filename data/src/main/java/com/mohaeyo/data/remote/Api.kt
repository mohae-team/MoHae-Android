package com.mohaeyo.data.remote

import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("signin")
    fun signIn(@Body body: Any?): Flowable<String>

    @POST("signup")
    fun signUp(@Body body: Any?): Flowable<String>
}
package com.mohaeyo.data.remote

import com.mohaeyo.data.dto.UserDto
import com.mohaeyo.data.entity.TokenData
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface Api {
    @POST("signin")
    fun signIn(@Body body: Any?): Flowable<TokenData>

    @POST("signup")
    fun signUp(@Body body: Any?): Flowable<TokenData>

    @Multipart
    @POST("mypage/edit")
    fun editProfile(
        @Part username: MultipartBody.Part,
        @Part address: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part imageFile: MultipartBody.Part): Flowable<UserDto>

    @GET("mypage/get")
    fun getProfile(): Flowable<UserDto>
}
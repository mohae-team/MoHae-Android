package com.mohaeyo.data.remote

import com.mohaeyo.data.dto.FeedbackDto
import com.mohaeyo.data.dto.GroupDto
import com.mohaeyo.data.dto.UserDto
import com.mohaeyo.data.entity.GroupData
import com.mohaeyo.data.entity.PlaceData
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

    @GET("place/get/{location}")
    fun getPlace(@Path("location") location: String): Flowable<PlaceData>

    @POST("place/post")
    fun postPlace(@Body body: Any?): Flowable<PlaceData>

    @POST("place/like")
    fun postLikePlace(@Body body: Any?): Flowable<PlaceData>

    @POST("place/dislike")
    fun postDisLikePlace(@Body body: Any?): Flowable<PlaceData>

    @GET("group/list")
    fun getGroupList(): Flowable<List<GroupDto>>

    @GET("group/detail/{id}")
    fun getGroupDetail(@Path("id") id: Int): Flowable<GroupDto>

    @POST("group/cancel/{id}")
    fun postGroupJoinCancel(@Path("id") id: Int): Flowable<GroupDto>

    @POST("group/join/{id}")
    fun postGroupJoin(@Path("id") id: Int): Flowable<GroupDto>

    @Multipart
    @POST("group/create")
    fun postCreateGroup(@Part title: MultipartBody.Part,
                        @Part location: MultipartBody.Part,
                        @Part address: MultipartBody.Part,
                        @Part term: MultipartBody.Part,
                        @Part summary: MultipartBody.Part,
                        @Part maxCount: MultipartBody.Part,
                        @Part description: MultipartBody.Part,
                        @Part imageFile: MultipartBody.Part): Flowable<GroupDto>

    @Multipart
    @POST("feedback/create")
    fun postCreateFeedback(@Part location: MultipartBody.Part,
                           @Part address: MultipartBody.Part,
                           @Part summary: MultipartBody.Part,
                           @Part description: MultipartBody.Part,
                           @Part imageFile: MultipartBody.Part): Flowable<FeedbackDto>

    @GET("feedback/list")
    fun getFeedbackList(): Flowable<List<FeedbackDto>>

    @GET("feedback/detail/{id}")
    fun getFeedbackDetail(@Path("id") id: Int): Flowable<FeedbackDto>

    @POST("feedback/like/{id}")
    fun postFeedbackLike(@Path("id") id: Int): Flowable<FeedbackDto>

    @POST("feedback/hate/{id}")
    fun postFeedbackHate(@Path("id") id: Int): Flowable<FeedbackDto>
}
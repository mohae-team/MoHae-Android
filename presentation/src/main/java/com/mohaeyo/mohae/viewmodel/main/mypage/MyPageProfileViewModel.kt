package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.ProfileModel

class MyPageProfileViewModel: BaseViewModel() {

    val startProfileEditEvent = SingleLiveEvent<Unit>()
    val startLoginEvent = SingleLiveEvent<Unit>()
    val startProfileData = MutableLiveData<ProfileModel>()

    init {
        startProfileData.value = ProfileModel(
            byteArrayOf(),
            "내 정보를 불러올 수 없습니다.",
            "네트워크 상태를 확인해주세요.",
            "네트워크 상태를 확인해주세요.",
            "네트워크 상태를 확인해주세요."
        )
    }

    fun clickLogout() {
        startLoginEvent.call()
    }

    fun clickEdit() {
        startProfileEditEvent.call()
    }

}
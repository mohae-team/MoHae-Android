package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.model.ProfileModel

class MyPageProfileEditViewModel: BaseViewModel() {

    val startProfileEvent = SingleLiveEvent<Unit>()

    val imageUrlData = MutableLiveData<String>()
    val nameData = MutableLiveData<String>()
    val addressData = MutableLiveData<String>()
    val emailData = MutableLiveData<String>()
    val descriptionData = MutableLiveData<String>()

    init {
        imageUrlData.value = "네트워크 상태를 확인해주세요."
        nameData.value = "내 정보를 불러올 수 없습니다."
        addressData.value = "네트워크 상태를 확인해주세요."
        emailData.value = "네트워크 상태를 확인해주세요."
        descriptionData.value = "네트워크 상태를 확인해주세요."
    }

    fun clickComplete() {
        startProfileEvent.call()
    }
}
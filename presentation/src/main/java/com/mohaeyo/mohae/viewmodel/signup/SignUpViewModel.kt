package com.mohaeyo.mohae.viewmodel.signup

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isValueBlank
import com.mohaeyo.mohae.model.MapMakerModel

class SignUpViewModel: BaseLocationViewModel() {

    val usernameText = MutableLiveData<String>()
    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val passwordCheckText = MutableLiveData<String>()

    val addressText = MutableLiveData<String>().apply { value = "거주 지역을 선택해주세요."}

    val nextBtnClickable = MediatorLiveData<Boolean>().apply {
        addSource(usernameText) { value = !usernameText.isValueBlank() && !idText.isValueBlank() && !passwordText.isValueBlank() && !passwordCheckText.isValueBlank() }
        addSource(idText) { value = !usernameText.isValueBlank() && !idText.isValueBlank() && !passwordText.isValueBlank() && !passwordCheckText.isValueBlank() }
        addSource(passwordText) { value = !usernameText.isValueBlank() && !idText.isValueBlank() && !passwordText.isValueBlank() && !passwordCheckText.isValueBlank() }
        addSource(passwordCheckText) { value =  !usernameText.isValueBlank() && !idText.isValueBlank() && !passwordText.isValueBlank() && !passwordCheckText.isValueBlank() }
    }

    val completeBtnClickable = MediatorLiveData<Boolean>().apply {
        addSource(addressText) { value = addressText.value!! != "거주 지역을 선택해주세요." }
    }

    val startSignInEvent = SingleLiveEvent<Unit>()
    val startSignUpEvent = SingleLiveEvent<Unit>()
    val startSignUpAddressEvent = SingleLiveEvent<Unit>()

    override fun updateAddressData(
        location: LatLng,
        addressTitle: String,
        addressSnippet: String,
        isSuccess: Boolean
    ) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = addressTitle, snippet = addressSnippet)
            addressText.value = addressTitle
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            addressText.value = "다른 지역을 선택해주세요."
        }
    }

    fun clickBackToSignUp() {startSignUpEvent.call() }

    fun clickBackToSignIn() { startSignInEvent.call() }

    fun clickSignUpNext() { startSignUpAddressEvent.call() }

    fun clickSignUpComplete() { startSignInEvent.call() }
}
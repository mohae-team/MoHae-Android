package com.mohaeyo.mohae.viewmodel.signup

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.usecase.SignUpUseCase
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isValueBlank
import com.mohaeyo.mohae.model.MapMakerModel
import io.reactivex.subscribers.DisposableSubscriber
import java.io.File

class SignUpViewModel(val signUpUseCase: SignUpUseCase): BaseLocationViewModel() {

    val usernameText = MutableLiveData<String>()
    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val passwordCheckText = MutableLiveData<String>()
    val addressText = MutableLiveData<String>().apply { value = "거주 지역을 선택해주세요"}

    val nextBtnClickable = MediatorLiveData<Boolean>().apply {
        addSource(usernameText) { value = !usernameText.isValueBlank() && !idText.isValueBlank() && !passwordText.isValueBlank() && !passwordCheckText.isValueBlank() }
        addSource(idText) { value = !usernameText.isValueBlank() && !idText.isValueBlank() && !passwordText.isValueBlank() && !passwordCheckText.isValueBlank() }
        addSource(passwordText) { value = !usernameText.isValueBlank() && !idText.isValueBlank() && !passwordText.isValueBlank() && !passwordCheckText.isValueBlank() }
        addSource(passwordCheckText) { value =  !usernameText.isValueBlank() && !idText.isValueBlank() && !passwordText.isValueBlank() && !passwordCheckText.isValueBlank() }
    }

    val completeBtnClickable = MediatorLiveData<Boolean>().apply {
        addSource(addressText) { value = addressText.value!! != "거주 지역을 선택해주세요" || addressText.value!! != "다른 지역을 선택해주세요" }
    }

    val startSignInEvent = SingleLiveEvent<Unit>()
    val startSignUpEvent = SingleLiveEvent<Unit>()
    val startSignUpAddressEvent = SingleLiveEvent<Unit>()

    val usernameErrorEvent = SingleLiveEvent<String>()
    val idErrorEvent = SingleLiveEvent<String>()
    val passwordErrorEvent = SingleLiveEvent<String>()
    val passwordCheckErrorEvent = SingleLiveEvent<String>()

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
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요", snippet = "다른 지역을 선택해주세요")
            addressText.value = "다른 지역을 선택해주세요"
        }
    }

    private fun signUpSuccess() {
        createToastEvent.value = "회원가입 되었습니다"
        startSignInEvent.call()
    }

    private fun signUpFail(message: String) {
        createToastEvent.value = message
    }

    fun clickBackToSignUp() { startSignUpEvent.call() }

    fun clickBackToSignIn() { startSignInEvent.call() }

    fun clickSignUpNext() {
        if (passwordText.value != passwordCheckText.value) {
            passwordErrorEvent.value = "비밀번호와 비밀번호 확인이 일치하지 않습니다"
            passwordCheckErrorEvent.value = "비밀번호와 비밀번호 확인이 일치하지 않습니다"
        } else {
            startSignUpAddressEvent.call()
        }
    }

    fun clickSignUpComplete() {
        val user = UserEntity(
            id = idText.value!!,
            password = passwordText.value!!,
            username = usernameText.value!!,
            imageFile = File(""),
            address = addressText.value!!,
            description = ""
        )
        signUpUseCase.execute(user, object: DisposableSubscriber<Pair<TokenEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<TokenEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) signUpSuccess()
                else signUpFail(t.second.message)
            }
            override fun onComplete() {

            }
            override fun onError(t: Throwable) {
                createToastEvent.value = "오류가 발생했습니다"
            }
        })
        startSignInEvent.call()
    }
}
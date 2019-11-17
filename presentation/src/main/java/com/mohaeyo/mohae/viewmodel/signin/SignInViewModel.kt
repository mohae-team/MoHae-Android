package com.mohaeyo.mohae.viewmodel.signin

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.usecase.SignInUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isValueBlank
import io.reactivex.subscribers.DisposableSubscriber

class SignInViewModel(val signInUseCase: SignInUseCase): BaseViewModel() {

    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()

    val btnClickable = MediatorLiveData<Boolean>().apply {
        addSource(idText) { value = !idText.isValueBlank() && !passwordText.isValueBlank()  }
        addSource(passwordText) { value = !idText.isValueBlank() && !passwordText.isValueBlank() }
    }

    val startMainEvent = SingleLiveEvent<Unit>()
    val startSignUpEvent = SingleLiveEvent<Unit>()
    val idErrorEvent = SingleLiveEvent<String>()
    val passwordErrorEvent = SingleLiveEvent<String>()

    private fun loginSuccess() {
        createToastEvent.value = "로그인 되었습니다"
        startMainEvent.call()
    }

    private fun loginFail(message: String) {
        idErrorEvent.value = message
        passwordErrorEvent.value = message
    }

    fun clickLogin() {
        val auth = AuthEntity(idText.value!!, passwordText.value!!)

        signInUseCase.execute(auth, object: DisposableSubscriber<Pair<TokenEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<TokenEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) loginSuccess()
                else loginFail(t.second.message)
            }
            override fun onComplete() {

            }
            override fun onError(t: Throwable?) {
                createToastEvent.value = "오류가 발생했습니다"
            }
        })
    }

    fun clickSignUp() { startSignUpEvent.call() }
}
package com.mohaeyo.mohae.viewmodel.signin

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.usecase.SignInUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.isNotValueBlank
import com.mohaeyo.mohae.mapper.AuthMapper
import com.mohaeyo.mohae.model.AuthModel
import io.reactivex.subscribers.DisposableSubscriber

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val authMapper: AuthMapper): BaseViewModel() {

    val idText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val btnClickable = MediatorLiveData<Boolean>().apply {
        addSource(idText) { value = checkFullText() }
        addSource(passwordText) { value = checkFullText() }
    }

    val startMainEvent = SingleLiveEvent<Unit>()
    val startSignUpEvent = SingleLiveEvent<Unit>()
    val idErrorEvent = SingleLiveEvent<String>()
    val passwordErrorEvent = SingleLiveEvent<String>()

    override fun apply(event: Lifecycle.Event) {

    }

    fun clickLogin() {
        val auth = AuthModel(idText.value!!, passwordText.value!!)

        signInUseCase.execute(authMapper.mapFrom(auth), object: DisposableSubscriber<Pair<TokenEntity, ErrorHandlerEntity>>() {
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

    fun clickSignUp() {
        startSignUpEvent.call()
    }

    private fun loginSuccess() {
        createToastEvent.value = "로그인 되었습니다"
        startMainEvent.call()
    }

    private fun loginFail(message: String) {
        idErrorEvent.value = message
        passwordErrorEvent.value = message
    }

    private fun checkFullText(): Boolean
            = idText.isNotValueBlank() && passwordText.isNotValueBlank()
}
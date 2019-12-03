package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.UserMapper
import com.mohaeyo.mohae.model.UserModel
import io.reactivex.subscribers.DisposableSubscriber

class MyPageProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val userMapper: UserMapper): BaseViewModel() {
    val userModel = MutableLiveData<UserModel>().apply {
        value = UserModel()
    }

    val startProfileEditEvent = SingleLiveEvent<Unit>()
    val startLoginEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> getMyPageInfo()
        }
    }

    fun clickLogout() {
        startLoginEvent.call()
    }

    fun clickEdit() {
        startProfileEditEvent.call()
    }

    private fun getMyPageInfo() {
        getUserProfileUseCase.execute(Unit, object: DisposableSubscriber<Pair<UserEntity, ErrorHandlerEntity>> () {
            override fun onNext(t: Pair<UserEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getSuccess(userMapper.mapEntityToModel(t.first))
                else getFail(t.second.message)
            }
            override fun onComplete() {

            }
            override fun onError(t: Throwable?) {
                getFail("알 수 없는 오류가 발생했습니다")
            }
        })
    }

    private fun getSuccess(user: UserModel) {
        userModel.value = user
    }

    private fun getFail(message: String) {
        createToastEvent.value = message
    }
}
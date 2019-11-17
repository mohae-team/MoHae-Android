package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.ProfileMapper
import com.mohaeyo.mohae.model.ProfileModel
import io.reactivex.subscribers.DisposableSubscriber

class MyPageProfileViewModel(
    val getUserProfileUseCase: GetUserProfileUseCase,
    val profileMapper: ProfileMapper): BaseViewModel() {

    val startProfileEditEvent = SingleLiveEvent<Unit>()
    val startLoginEvent = SingleLiveEvent<Unit>()
    val startProfileData = MutableLiveData<ProfileModel>()

    val mapUserToProfile: (UserEntity) -> ProfileModel
            = { profileMapper.mapFrom(it) }

    init {
        getUserProfileUseCase.execute(Unit, object: DisposableSubscriber<Pair<UserEntity, ErrorHandlerEntity>> () {
            override fun onNext(t: Pair<UserEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getSuccess(mapUserToProfile(t.first))
                else getFail(t.second.message)
            }
            override fun onComplete() {

            }
            override fun onError(t: Throwable?) {
                getFail("알 수 없는 오류가 발생했습니다")
            }
        })
    }

    private fun getSuccess(profile: ProfileModel) {
        startProfileData.value = profile
    }

    private fun getFail(message: String) {
        createToastEvent.value = message
    }

    fun clickLogout() {
        startLoginEvent.call()
    }

    fun clickEdit() {
        startProfileEditEvent.call()
    }

}
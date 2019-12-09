package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.UserMapper
import com.mohaeyo.mohae.model.MapMakerModel
import com.mohaeyo.mohae.model.UserModel
import io.reactivex.subscribers.DisposableSubscriber

class MyPageProfileEditViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val editUserProfileUseCase: EditUserProfileUseCase,
    private val userMapper: UserMapper): BaseLocationViewModel() {

    val userModel = MutableLiveData<UserModel>().apply {
        value = UserModel()
    }

    val startProfileEvent = SingleLiveEvent<Unit>()
    val getProfileImageEvent = SingleLiveEvent<Unit>()
    val descriptionErrorEvent = SingleLiveEvent<String>()

    override fun apply(event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> getMyPageInfo()
        }
    }

    override fun updateAddressData(
        location: LatLng,
        addressTitle: String,
        addressSnippet: String,
        isSuccess: Boolean
    ) {
        if (isSuccess) {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = addressTitle, snippet = addressSnippet)
            userModel.value!!.address = addressTitle
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            userModel.value!!.address = "다른 지역을 선택해주세요."
        }
    }

    fun clickImageEdit() {
        getProfileImageEvent.call()
    }

    fun clickComplete() {
        if (checkFullData()) postMyPageInfo()
        else createToastEvent.value = "전부 채워주세요"
    }

    private fun postMyPageInfo() {
        editUserProfileUseCase.execute(userMapper.mapFrom(userModel.value!!), object: DisposableSubscriber<Pair<UserEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<UserEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) editSuccess()
                else editFail(t.second.message)
            }
            override fun onComplete() {

            }
            override fun onError(t: Throwable?) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    private fun getMyPageInfo() {
        getUserProfileUseCase.execute(Unit, object: DisposableSubscriber<Pair<UserEntity, ErrorHandlerEntity>>() {
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

    fun getSuccess(user: UserModel) {
        userModel.value = user
    }

    fun getFail(message: String) {
        createToastEvent.value = message
    }

    fun editSuccess() {
        createToastEvent.value = "수정이 완료되었습니다"
    }

    fun editFail(message: String) {
        createToastEvent.value = message
    }

    private fun checkFullData(): Boolean
            = with(userModel.value!!) {
        username.isNotBlank() && id.isNotBlank() && address.isNotBlank() && description.isNotBlank()
    }
}
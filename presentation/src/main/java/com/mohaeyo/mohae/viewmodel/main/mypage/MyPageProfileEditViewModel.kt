package com.mohaeyo.mohae.viewmodel.main.mypage

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.domain.usecase.SignInUseCase
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.ProfileMapper
import com.mohaeyo.mohae.model.MapMakerModel
import com.mohaeyo.mohae.model.ProfileModel
import io.reactivex.subscribers.DisposableSubscriber
import java.io.File

class MyPageProfileEditViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val editUserProfileUseCase: EditUserProfileUseCase,
    private val profileMapper: ProfileMapper): BaseLocationViewModel() {

    val imageFile = MutableLiveData<File>()
    val nameText = MutableLiveData<String>()
    val addressText = MutableLiveData<String>()
    val idText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()

    val startProfileEvent = SingleLiveEvent<Unit>()
    val getProfileImageEvent = SingleLiveEvent<Unit>()
    val setProfileImageEvent = SingleLiveEvent<Unit>()
    val descriptionErrorEvent = SingleLiveEvent<String>()

    override fun apply(event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> getMyPageInfo()
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
            addressText.value = addressTitle
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            addressText.value = "다른 지역을 선택해주세요."
        }
    }

    fun clickImageEdit() {
        getProfileImageEvent.call()
    }

    fun clickComplete() {
        if (!(nameText.value.isNullOrBlank()) && !(idText.value.isNullOrBlank())
            && !(addressText.value.isNullOrBlank()) && !(descriptionText.value.isNullOrBlank())) {
            val user = UserEntity(
                username = nameText.value!!,
                id = idText.value!!,
                password = "",
                imageFile = imageFile.value!!,
                address = addressText.value!!,
                description = descriptionText.value!!
            )
            editUserProfileUseCase.execute(user, object: DisposableSubscriber<Pair<UserEntity, ErrorHandlerEntity>>() {
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
        } else createToastEvent.value = "전부 채워주세요"
    }

    private fun getMyPageInfo() {
        getUserProfileUseCase.execute(Unit, object: DisposableSubscriber<Pair<UserEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<UserEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getSuccess(profileMapper.mapFrom(t.first))
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
        imageFile.value = profile.imageFile
        nameText.value = profile.name
        addressText.value = profile.address
        idText.value = profile.id
        descriptionText.value = profile.description

        setProfileImageEvent.call()
    }

    private fun getFail(message: String) {
        createToastEvent.value = message
    }

    private fun editSuccess() {
        createToastEvent.value = "수정이 완료되었습니다"
        startProfileEvent.call()
    }

    private fun editFail(message: String) {
        createToastEvent.value = message
    }
}
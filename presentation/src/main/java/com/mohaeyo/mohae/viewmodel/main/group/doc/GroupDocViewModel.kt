package com.mohaeyo.mohae.viewmodel.main.group.doc

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.usecase.CreateGroupUseCase
import com.mohaeyo.mohae.base.BaseLocationViewModel
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.model.MapMakerModel
import io.reactivex.subscribers.DisposableSubscriber

class GroupDocViewModel(
    private val createGroupUseCase: CreateGroupUseCase,
    private val groupMapper: GroupMapper
): BaseLocationViewModel() {

    val postGroupModel
            = MutableLiveData<GroupModel>().apply { value = GroupModel() }

    val startDocToListEvent = SingleLiveEvent<Unit>()
    val errorEvent = SingleLiveEvent<String>()
    val setGroupImageEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {

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
            postGroupModel.value!!.address = addressTitle
            postGroupModel.value!!.location = addressSnippet
        } else {
            drawMarkerEvent.value =
                MapMakerModel(location = location, title = "다른 지역을 선택해주세요.", snippet = "다른 지역을 선택해주세요.")
            postGroupModel.value!!.address = "다른 지역을 선택해주세요."
            postGroupModel.value!!.location = "다른 지역을 선택해주세요."
        }
    }

    fun clickPostGroup() {
        if (with(postGroupModel.value!!) {
                title.isNotBlank() && description.isNotBlank() && term.isNotBlank() && summary.isNotBlank()}) {
                createGroupUseCase.execute(groupMapper.mapFrom(postGroupModel.value!!),
                    object: DisposableSubscriber<Pair<GroupEntity, ErrorHandlerEntity>>() {
                        override fun onNext(t: Pair<GroupEntity, ErrorHandlerEntity>) {
                            if (t.second.isSuccess) createGroupSuccess()
                            else createGroupFail(t.second.message)
                        }

                        override fun onComplete() {

                        }

                        override fun onError(t: Throwable) {
                            createToastEvent.value = "알 수 없는 오류가 발생했습니다"
                        }
                    })
            } else {
            createToastEvent.value = "전부 채워주세요"
            errorEvent.value = "전부 채워주세요"
        }
    }

    fun clickSetImage() {
        setGroupImageEvent.call()
    }

    fun clickDocToList() {
        startDocToListEvent.call()
    }

    fun createGroupSuccess() {
        startDocToListEvent.call()
    }

    fun createGroupFail(message: String) {
        createToastEvent.value = message
        errorEvent.value = message
    }
}
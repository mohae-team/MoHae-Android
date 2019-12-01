package com.mohaeyo.mohae.viewmodel.main.group.detail

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.usecase.CancelJoinGroupUseCase
import com.mohaeyo.domain.usecase.GetGroupDetailUseCase
import com.mohaeyo.domain.usecase.JoinGroupUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.model.GroupModel
import io.reactivex.subscribers.DisposableSubscriber

class GroupDetailViewModel(
    private val getGroupDetailUseCase: GetGroupDetailUseCase,
    private val joinGroupUseCase: JoinGroupUseCase,
    private val cancelJoinGroupUseCase: CancelJoinGroupUseCase,
    private val groupMapper: GroupMapper
): BaseViewModel(), LifecycleCallback {

    val selectedGroupId = MutableLiveData<Int>()
    val selectedGroupItem = MutableLiveData<GroupModel>()

    val startDetailToListEvent = SingleLiveEvent<Unit>()
    val startDetailToDialogEvent = SingleLiveEvent<Unit>()
    val closeDialog = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> getGroupDetail()
        }
    }

    private fun getGroupDetail() {
        getGroupDetailUseCase.execute(selectedGroupId.value!!, object: DisposableSubscriber<Pair<GroupEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<GroupEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getDetailSuccess(groupMapper.mapEntityToModel(t.first))
                else getDetailFail(t.second.message, groupMapper.mapEntityToModel(t.first))
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun clickDetailToList() {
        startDetailToListEvent.call()
    }

    fun clickJoinGroup() {
        startDetailToDialogEvent.call()
    }

    fun clickJoin() {
        joinGroupUseCase.execute(selectedGroupId.value!!, object: DisposableSubscriber<Pair<GroupEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<GroupEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) joinSuccess(groupMapper.mapEntityToModel(t.first))
                else joinFail(t.second.message, groupMapper.mapEntityToModel(t.first))
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
        closeDialog.call()
    }

    fun clickLeave() {
        cancelJoinGroupUseCase.execute(selectedGroupId.value!!, object: DisposableSubscriber<Pair<GroupEntity, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<GroupEntity, ErrorHandlerEntity>) {
                if (t.second.isSuccess) cancelSuccess(groupMapper.mapEntityToModel(t.first))
                else cancelFail(t.second.message, groupMapper.mapEntityToModel(t.first))
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
        closeDialog.call()
    }

    private fun getDetailSuccess(group: GroupModel) {
        selectedGroupItem.value = group
    }

    private fun getDetailFail(message: String, group: GroupModel) {
        createToastEvent.value = message
        selectedGroupItem.value = group
    }

    private fun joinSuccess(group: GroupModel) {
        selectedGroupItem.value = group
    }

    private fun joinFail(message: String, group: GroupModel) {
        createToastEvent.value = message
        selectedGroupItem.value = group
    }

    private fun cancelSuccess(group: GroupModel) {
        selectedGroupItem.value = group
    }

    private fun cancelFail(message: String, group: GroupModel) {
        createToastEvent.value = message
        selectedGroupItem.value = group
    }
}
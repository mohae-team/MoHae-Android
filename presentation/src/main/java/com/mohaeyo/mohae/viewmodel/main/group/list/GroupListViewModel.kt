package com.mohaeyo.mohae.viewmodel.main.group.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.usecase.GetGroupListUseCase
import com.mohaeyo.mohae.base.BaseViewModel
import com.mohaeyo.mohae.base.LifecycleCallback
import com.mohaeyo.mohae.base.SingleLiveEvent
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.model.GroupModel
import io.reactivex.subscribers.DisposableSubscriber

class GroupListViewModel(
    private val getGroupListUseCase: GetGroupListUseCase,
    private val groupMapper: GroupMapper
): BaseViewModel(), LifecycleCallback {

    val groupList = MutableLiveData<ArrayList<GroupModel>>()
        .apply {
            value = ArrayList(emptyList())
    }

    val startListToDetailEvent = SingleLiveEvent<GroupModel>()
    val startListToDocEvent = SingleLiveEvent<Unit>()
    val listAnimationEvent = SingleLiveEvent<Unit>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> getGroupList()
        }
    }

    private fun getGroupList() {
        getGroupListUseCase.execute(Unit, object: DisposableSubscriber<Pair<List<GroupEntity>, ErrorHandlerEntity>>() {
            override fun onNext(t: Pair<List<GroupEntity>, ErrorHandlerEntity>) {
                if (t.second.isSuccess) getListSuccess(t.first.map { groupMapper.mapEntityToModel(it) })
                else getListFail(t.second.message, t.first.map { groupMapper.mapEntityToModel(it) })
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {
                createToastEvent.value = "알 수 없는 오류가 발생했습니다"
            }
        })
    }

    fun clickListToDoc() {
        startListToDocEvent.call()
    }

    fun clickGroupItem(group: GroupModel) {
        startListToDetailEvent.value = group
    }

    private fun getListSuccess(groupList: List<GroupModel>) {
        this.groupList.value = ArrayList(groupList)
        listAnimationEvent.call()
    }

    private fun getListFail(message: String, groupList: List<GroupModel>) {
        createToastEvent.value = message
        this.groupList.value = ArrayList(groupList)
        listAnimationEvent.call()
    }
}
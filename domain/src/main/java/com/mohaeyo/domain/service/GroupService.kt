package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.GroupEntity
import io.reactivex.Flowable

interface GroupService {
    fun getListGroup(): Flowable<Pair<List<GroupEntity>, ErrorHandlerEntity>>

    fun getGroupDetail(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>

    fun createGroup(group: GroupEntity): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>

    fun cancelGroup(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>

    fun joinGroup(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
}
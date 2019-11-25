package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.GroupDto
import com.mohaeyo.data.entity.GroupData
import com.mohaeyo.data.local.database.entity.Group
import io.reactivex.Flowable

interface GroupDataSource {
    fun getRemoteListGroup(): Flowable<List<GroupDto>>

    fun getRemoteGroupDetail(id: Int): Flowable<GroupDto>

    fun getLocalListGroup(): List<Group>

    fun getLocalGroupDetail(id: Int): Group

    fun saveLocalGroup(group: Group)

    fun saveLocalGroupList(groupList: List<Group>)

    fun createGroup(group: GroupData): Flowable<GroupDto>

    fun cancelGroup(id: Int): Flowable<GroupDto>

    fun joinGroup(id: Int): Flowable<GroupDto>
}
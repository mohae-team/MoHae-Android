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

    fun postCreateGroup(group: GroupData): Flowable<GroupDto>

    fun postCancelGroup(id: Int): Flowable<GroupDto>

    fun postJoinGroup(id: Int): Flowable<GroupDto>
}
package com.mohaeyo.domain.repository

import com.mohaeyo.domain.entity.GroupEntity
import io.reactivex.Flowable

interface GroupRepository {
    fun getRemoteListGroup(): Flowable<List<GroupEntity>>

    fun getRemoteGroupDetail(id: Int): Flowable<GroupEntity>

    fun getLocalListGroup(): List<GroupEntity>

    fun getLocalGroupDetail(id: Int): GroupEntity

    fun saveLocalGroup(group: GroupEntity)

    fun saveLocalGroupList(groupList: List<GroupEntity>)

    fun createGroup(group: GroupEntity): Flowable<GroupEntity>

    fun cancelGroup(id: Int): Flowable<GroupEntity>

    fun joinGroup(id: Int): Flowable<GroupEntity>
}
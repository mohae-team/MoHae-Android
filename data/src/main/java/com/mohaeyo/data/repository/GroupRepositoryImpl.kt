package com.mohaeyo.data.repository

import com.mohaeyo.data.datasource.GroupDataSource
import com.mohaeyo.data.mapper.GroupDataMapper
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.repository.GroupRepository
import io.reactivex.Flowable

class GroupRepositoryImpl(
    private val datasource: GroupDataSource,
    private val groupDataMapper: GroupDataMapper
): GroupRepository {

    override fun getRemoteListGroup(): Flowable<List<GroupEntity>>
            = datasource.getRemoteListGroup().map { list -> list.map { groupDataMapper.mapDtoToEntity(it) } }

    override fun getRemoteGroupDetail(id: Int): Flowable<GroupEntity>
            = datasource.getRemoteGroupDetail(id).map { groupDataMapper.mapDtoToEntity(it) }

    override fun getLocalListGroup(): List<GroupEntity>
            = datasource.getLocalListGroup().map { groupDataMapper.mapDbToEntity(it) }

    override fun getLocalGroupDetail(id: Int): GroupEntity
            = groupDataMapper.mapDbToEntity(datasource.getLocalGroupDetail(id))

    override fun saveLocalGroup(group: GroupEntity)
            = datasource.saveLocalGroup(groupDataMapper.mapEntityToDb(group))

    override fun saveLocalGroupList(groupList: List<GroupEntity>)
            = datasource.saveLocalGroupList(groupList.map { groupDataMapper.mapEntityToDb(it) })

    override fun createGroup(group: GroupEntity): Flowable<GroupEntity>
            = datasource.createGroup(groupDataMapper.mapFrom(group))
        .map { groupDataMapper.mapDtoToEntity(it) }

    override fun cancelGroup(id: Int): Flowable<GroupEntity>
            = datasource.cancelGroup(id).map { groupDataMapper.mapDtoToEntity(it) }

    override fun joinGroup(id: Int): Flowable<GroupEntity>
            = datasource.joinGroup(id).map { groupDataMapper.mapDtoToEntity(it) }
}
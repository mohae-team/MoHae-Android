package com.mohaeyo.data.datasource

import com.mohaeyo.data.dto.GroupDto
import com.mohaeyo.data.entity.GroupData
import com.mohaeyo.data.local.database.dao.GroupDao
import com.mohaeyo.data.local.database.entity.Group
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.toImageRequestBody
import io.reactivex.Flowable
import okhttp3.MultipartBody

class GroupDataSourceImpl(
    private val api: Api,
    private val groupDao: GroupDao
): GroupDataSource {
    override fun getRemoteListGroup(): Flowable<List<GroupDto>>
            = api.getGroupList().map { it.toList() }

    override fun getRemoteGroupDetail(id: Int): Flowable<GroupDto>
            = api.getGroupDetail(id)

    override fun getLocalListGroup(): List<Group>
            = groupDao.getGroupList()

    override fun getLocalGroupDetail(id: Int): Group
            = groupDao.getGroup(id)

    override fun saveLocalGroup(group: Group)
            = groupDao.saveGroup(group)

    override fun saveLocalGroupList(groupList: List<Group>)
            = groupDao.saveGroup(*groupList.toTypedArray())

    override fun postCreateGroup(group: GroupData): Flowable<GroupDto>
            = api.postCreateGroup(
        title = MultipartBody.Part.createFormData("title", group.title),
        imageFile = MultipartBody.Part.createFormData("imageFile", group.imageFile.name, group.imageFile.toImageRequestBody()),
        term = MultipartBody.Part.createFormData("term", group.term),
        description = MultipartBody.Part.createFormData("description", group.description),
        address = MultipartBody.Part.createFormData("address", group.address),
        summary = MultipartBody.Part.createFormData("summary", group.summary),
        location = MultipartBody.Part.createFormData("location", group.location),
        maxCount = MultipartBody.Part.createFormData("maxCount", group.maxCount.toString())
    )

    override fun postCancelGroup(id: Int): Flowable<GroupDto>
            = api.postGroupJoinCancel(id)

    override fun postJoinGroup(id: Int): Flowable<GroupDto>
            = api.postGroupJoin(id)
}
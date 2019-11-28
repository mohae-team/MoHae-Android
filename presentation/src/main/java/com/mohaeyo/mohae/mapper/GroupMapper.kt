package com.mohaeyo.mohae.mapper

import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.mapper.Mapper
import com.mohaeyo.mohae.model.GroupModel

class GroupMapper: Mapper<GroupModel, GroupEntity> {
    override fun mapFrom(from: GroupModel): GroupEntity
            = GroupEntity(
        id = from.id,
        title = from.title,
        address = from.address,
        location = from.location,
        imageFile = from.imageFile,
        count = from.count,
        description = from.description,
        summary = from.summary,
        maxCount = from.maxCount,
        term = from.term,
        isJoin = from.isJoin
    )

    fun mapEntityToModel(from: GroupEntity): GroupModel
            = GroupModel(
        id = from.id,
        title = from.title,
        address = from.address,
        location = from.location,
        imageFile = from.imageFile,
        count = from.count,
        countText = "${from.count}/${from.maxCount}",
        description = from.description,
        summary = from.summary,
        maxCount = from.maxCount,
        term = from.term,
        isJoin = from.isJoin
    )
}
package com.mohaeyo.data.mapper

import com.mohaeyo.data.dto.GroupDto
import com.mohaeyo.data.entity.GroupData
import com.mohaeyo.data.local.database.entity.Group
import com.mohaeyo.data.toFile
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.mapper.Mapper

class GroupDataMapper: Mapper<GroupEntity, GroupData> {
    override fun mapFrom(from: GroupEntity): GroupData
            = GroupData(
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

    fun mapDataToEntity(from: GroupData): GroupEntity
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

    fun mapDbToEntity(from: Group): GroupEntity
            = GroupEntity(
        id = from.id,
        title = from.title,
        address = from.address,
        location = from.location,
        imageFile = from.imageUri.toFile(),
        count = from.count,
        description = from.description,
        summary = from.summary,
        maxCount = from.maxCount,
        term = from.term,
        isJoin = from.isJoin
    )

    fun mapEntityToDb(from: GroupEntity): Group
            = Group(
        id = from.id,
        title = from.title,
        address = from.address,
        location = from.location,
        imageUri = from.imageFile.toString(),
        count = from.count,
        description = from.description,
        summary = from.summary,
        maxCount = from.maxCount,
        term = from.term,
        isJoin = from.isJoin
    )

    fun mapDtoToEntity(from: GroupDto): GroupEntity
            = GroupEntity(
        id = from.id,
        title = from.title,
        address = from.address,
        location = from.location,
        imageFile = from.imageUri.toFile(),
        count = from.count,
        description = from.description,
        summary = from.summary,
        maxCount = from.maxCount,
        term = from.term,
        isJoin = from.join
    )
}
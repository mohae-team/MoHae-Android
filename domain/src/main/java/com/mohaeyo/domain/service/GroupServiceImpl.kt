package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.handler.GroupErrorHandler
import com.mohaeyo.domain.repository.GroupRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class GroupServiceImpl(
    private val groupRepository: GroupRepository,
    private val groupErrorHandler: GroupErrorHandler): GroupService {
    override fun getListGroup(): Flowable<Pair<List<GroupEntity>,ErrorHandlerEntity>>
            = groupRepository.getRemoteGroupList().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        groupRepository.saveLocalGroupList(it.first)
    }.onErrorReturn {
        groupRepository.getLocalGroupList() to groupErrorHandler.getListErrorHandle(it)
    }

    override fun getGroupDetail(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupRepository.getRemoteGroupDetail(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        groupRepository.saveLocalGroup(it.first)
    }.onErrorReturn {
        groupRepository.getLocalGroupDetail(id) to groupErrorHandler.getDetailErrorHandle(it)
    }

    override fun createGroup(group: GroupEntity): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupRepository.createGroup(group).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        group to groupErrorHandler.createErrorHandle(it)
    }

    override fun cancelGroup(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupRepository.cancelGroup(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        groupRepository.getLocalGroupDetail(id) to groupErrorHandler.cancelErrorHandle(it)
    }

    override fun joinGroup(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupRepository.joinGroup(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        groupRepository.getLocalGroupDetail(id) to groupErrorHandler.joinErrorHandle(it)
    }
}
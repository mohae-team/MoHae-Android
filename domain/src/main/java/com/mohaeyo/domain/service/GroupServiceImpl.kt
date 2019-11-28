package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.repository.GroupRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class GroupServiceImpl (private val groupRepository: GroupRepository): GroupService {
    override fun getListGroup(): Flowable<Pair<List<GroupEntity>,ErrorHandlerEntity>>
            = groupRepository.getRemoteGroupList().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        groupRepository.saveLocalGroupList(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            groupRepository.getLocalGroupList() to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "아직 모임이 없습니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else groupRepository.getLocalGroupList() to ErrorHandlerEntity(isSuccess = true)
    }

    override fun getGroupDetail(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupRepository.getRemoteGroupDetail(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        groupRepository.saveLocalGroup(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            groupRepository.getLocalGroupDetail(id) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 게시물입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else groupRepository.getLocalGroupDetail(id) to ErrorHandlerEntity(isSuccess = true)
    }

    override fun createGroup(group: GroupEntity): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupRepository.createGroup(group).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            group to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 게시물입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else group to ErrorHandlerEntity(isSuccess = true)
    }

    override fun cancelGroup(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupRepository.cancelGroup(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            groupRepository.getLocalGroupDetail(id) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 게시물입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else groupRepository.getLocalGroupDetail(id) to ErrorHandlerEntity(isSuccess = true)
    }

    override fun joinGroup(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupRepository.joinGroup(id).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            groupRepository.getLocalGroupDetail(id) to ErrorHandlerEntity(message =
            when(it.code()) {
                404 -> "존재하지 않는 게시물입니다"
                403 -> "권한이 없습니다"
                500 -> "서버 에러가 발생했습니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else groupRepository.getLocalGroupDetail(id) to ErrorHandlerEntity(isSuccess = true)
    }
}
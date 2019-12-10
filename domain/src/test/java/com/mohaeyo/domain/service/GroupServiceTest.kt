package com.mohaeyo.domain.service

import com.mohaeyo.domain.BaseTest
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.handler.GroupErrorHandler
import com.mohaeyo.domain.repository.GroupRepository
import com.mohaeyo.domain.usecase.*
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*

class GroupServiceTest: BaseTest() {
    @Mock
    private lateinit var groupRepository: GroupRepository
    @Mock
    private lateinit var groupErrorHandler: GroupErrorHandler

    private lateinit var groupService: GroupService

    private lateinit var getGroupListUseCase: GetGroupListUseCase
    private lateinit var getGroupDetailUseCase: GetGroupDetailUseCase
    private lateinit var createGroupUseCase: CreateGroupUseCase
    private lateinit var cancelGroupUseCase: CancelJoinGroupUseCase
    private lateinit var joinGroupUseCase: JoinGroupUseCase

    @Before
    fun init() {
        groupService = GroupServiceImpl(groupRepository, groupErrorHandler)

        getGroupListUseCase = GetGroupListUseCase(groupService, CompositeDisposable())
        getGroupDetailUseCase = GetGroupDetailUseCase(groupService, CompositeDisposable())
        createGroupUseCase = CreateGroupUseCase(groupService, CompositeDisposable())
        cancelGroupUseCase = CancelJoinGroupUseCase(groupService, CompositeDisposable())
        joinGroupUseCase = JoinGroupUseCase(groupService, CompositeDisposable())
    }

    @Test
    fun `그룹 리스트 가져오기 성공 테스트`() {
        `when`(groupRepository.getRemoteGroupList())
            .thenReturn(Flowable.just(emptyList()))

        getGroupListUseCase.createFlowable(
            Unit
        ).test().assertValue(emptyList<GroupEntity>() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `그룹 리스트 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(groupRepository.getRemoteGroupList())
            .thenReturn(Flowable.error(throwable))

        `when`(groupRepository.getLocalGroupList())
            .thenReturn(emptyList())

        `when`(groupErrorHandler.getListErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getGroupListUseCase.createFlowable(
            Unit
        ).test().assertValue(emptyList<GroupEntity>() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `그룹 개별정보 가져오기 성공 테스트`() {
        `when`(groupRepository.getRemoteGroupDetail(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.just(GroupEntity()))

        getGroupDetailUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(GroupEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `그룹 개별정보 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(groupRepository.getRemoteGroupDetail(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.error(throwable))

        `when`(groupRepository.getLocalGroupDetail(ArgumentMatchers.anyInt()))
            .thenReturn(GroupEntity())

        `when`(groupErrorHandler.getDetailErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getGroupDetailUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(GroupEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `그룹 만들기 성공 테스트`() {
        `when`(groupRepository.createGroup(GroupEntity()))
            .thenReturn(Flowable.just(GroupEntity()))

        createGroupUseCase.createFlowable(
            GroupEntity()
        ).test().assertValue(GroupEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `그룹 만들기 실패 테스트`() {
        val throwable = Throwable()

        `when`(groupRepository.createGroup(GroupEntity()))
            .thenReturn(Flowable.error(throwable))

        `when`(groupErrorHandler.createErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        createGroupUseCase.createFlowable(
            GroupEntity()
        ).test().assertValue(GroupEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `그룹 참가하기 성공 테스트`() {
        `when`(groupRepository.joinGroup(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.just(GroupEntity()))

        joinGroupUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(GroupEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `그룹 참가하기 실패 테스트`() {
        val throwable = Throwable()

        `when`(groupRepository.joinGroup(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.error(throwable))

        `when`(groupRepository.getLocalGroupDetail(ArgumentMatchers.anyInt()))
            .thenReturn(GroupEntity())

        `when`(groupErrorHandler.joinErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        joinGroupUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(GroupEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `그룹 나가기 성공 테스트`() {
        `when`(groupRepository.cancelGroup(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.just(GroupEntity()))

        cancelGroupUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(GroupEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `그룹 나가기 실패 테스트`() {
        val throwable = Throwable()

        `when`(groupRepository.cancelGroup(ArgumentMatchers.anyInt()))
            .thenReturn(Flowable.error(throwable))

        `when`(groupRepository.getLocalGroupDetail(ArgumentMatchers.anyInt()))
            .thenReturn(GroupEntity())

        `when`(groupErrorHandler.cancelErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        cancelGroupUseCase.createFlowable(
            ArgumentMatchers.anyInt()
        ).test().assertValue(GroupEntity() to ErrorHandlerEntity(isSuccess = false))
    }
}
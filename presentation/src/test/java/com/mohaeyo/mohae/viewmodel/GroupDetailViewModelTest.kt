package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.CancelJoinGroupUseCase
import com.mohaeyo.domain.usecase.GetGroupDetailUseCase
import com.mohaeyo.domain.usecase.JoinGroupUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.group.detail.GroupDetailViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GroupDetailViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getGroupDetailUseCase: GetGroupDetailUseCase
    @Mock
    private lateinit var joinGroupUseCase: JoinGroupUseCase
    @Mock
    private lateinit var cancelJoinGroupUseCase: CancelJoinGroupUseCase

    private lateinit var groupDetailViewModel: GroupDetailViewModel
    private lateinit var groupMapper: GroupMapper

    @Before
    fun init() {
        groupMapper = GroupMapper()
        groupDetailViewModel = GroupDetailViewModel(
            getGroupDetailUseCase, joinGroupUseCase, cancelJoinGroupUseCase, groupMapper)
    }

    @Test
    fun `그룹 정보를 가져오는 행위 성공 알림 테스트`() {
        groupDetailViewModel.run {
            val result = GroupModel(id = 100)
            getDetailSuccess(result)

            selectedGroupItem.test().assertValue(result)
        }
    }

    @Test
    fun `그룹 정보를 가져오는 행위 실패 알림 테스트`() {
        groupDetailViewModel.run {
            val result = GroupModel(id = 100)
            val failMessage = "그룹 정보를 가져올 수 없습니다."
            getDetailFail(failMessage, result)

            createToastEvent.test().assertValue(failMessage)
            selectedGroupItem.test().assertValue(result)
        }
    }

    @Test
    fun `그룹에 참여하는 행위 성공 알림 테스트`() {
        groupDetailViewModel.run {
            val result = GroupModel(id = 100)
            joinSuccess(result)

            selectedGroupItem.test().assertValue(result)
        }
    }

    @Test
    fun `그룹에 참여하는 행위 실패 알림 테스트`() {
        groupDetailViewModel.run {
            val result = GroupModel(id = 100)
            val failMessage = "그룹 정보를 가져올 수 없습니다."

            joinFail(failMessage, result)

            createToastEvent.test().assertValue(failMessage)
            selectedGroupItem.test().assertValue(result)
        }
    }

    @Test
    fun `그룹에서 나가는 행위 성공 알림 테스트`() {
        groupDetailViewModel.run {
            val result = GroupModel(id = 100)
            cancelSuccess(result)

            selectedGroupItem.test().assertValue(result)
        }
    }

    @Test
    fun `그룹에서 나가는 행위 실패 알림 테스트`() {
        groupDetailViewModel.run {
            val result = GroupModel(id = 100)
            val failMessage = "그룹 정보를 가져올 수 없습니다."

            cancelFail(failMessage, result)

            createToastEvent.test().assertValue(failMessage)
            selectedGroupItem.test().assertValue(result)
        }
    }
}
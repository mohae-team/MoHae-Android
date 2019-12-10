package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.CreateGroupUseCase
import com.mohaeyo.domain.usecase.GetGroupListUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.group.doc.GroupDocViewModel
import com.mohaeyo.mohae.viewmodel.main.group.list.GroupListViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GroupDocViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var createGroupUseCase: CreateGroupUseCase

    private lateinit var groupDocViewModel: GroupDocViewModel
    private lateinit var groupMapper: GroupMapper

    @Before
    fun init() {
        groupMapper = GroupMapper()
        groupDocViewModel = GroupDocViewModel(createGroupUseCase, groupMapper)
    }

    @Test
    fun `그룹 만드는 행위 성공 알림 테스트`() {
        groupDocViewModel.run {
            createGroupSuccess()

            startDocToListEvent.test().assertHasValue()
        }
    }

    @Test
    fun `그룹 만드는 행위 실패 알림 테스트`() {
        groupDocViewModel.run {
            val failMessage = "해당 그룹을 만들 수 없습니다."
            createGroupFail(failMessage)

            createToastEvent.test().assertValue(failMessage)
            errorEvent.test().assertValue(failMessage)
        }
    }

    @Test
    fun `이미지 가져오는 이벤트 테스트`() {
        groupDocViewModel.run {
            clickSetImage()
            setGroupImageEvent.test().assertHasValue()
        }
    }
}
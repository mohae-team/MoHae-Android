package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.GetGroupListUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.GroupMapper
import com.mohaeyo.mohae.model.GroupModel
import com.mohaeyo.mohae.viewmodel.main.group.list.GroupListViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GroupListViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getGroupListUseCase: GetGroupListUseCase

    private lateinit var groupListViewModel: GroupListViewModel
    private lateinit var groupMapper: GroupMapper

    @Before
    fun init() {
        groupMapper = GroupMapper()
        groupListViewModel = GroupListViewModel(getGroupListUseCase, groupMapper)
    }

    @Test
    fun `그룹 리스트 가져오는 행위 성공 알림 테스트`() {
        groupListViewModel.run {
            getListSuccess(emptyList())

            groupList.test().assertValue(
                ArrayList(emptyList<GroupModel>())
            )
            listAnimationEvent.test().assertHasValue()
        }
    }

    @Test
    fun `그룹 리스트 가져오는 행위 실패 알림 테스트`() {
        groupListViewModel.run {
            val failMessage = "그룹 리스트를 가져올 수 없습니다."

            getListFail(failMessage, emptyList())

            createToastEvent.test().assertValue(failMessage)
            groupList.test().assertValue(
                ArrayList(emptyList<GroupModel>())
            )
            listAnimationEvent.test().assertHasValue()
        }
    }
}
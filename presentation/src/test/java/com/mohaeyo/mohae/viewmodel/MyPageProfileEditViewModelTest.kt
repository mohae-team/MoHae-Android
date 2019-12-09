package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.UserMapper
import com.mohaeyo.mohae.model.UserModel
import com.mohaeyo.mohae.viewmodel.main.mypage.MyPageProfileEditViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import java.io.File

class MyPageProfileEditViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getUserProfileUseCase: GetUserProfileUseCase
    @Mock
    private lateinit var editUserProfileUseCase: EditUserProfileUseCase

    private lateinit var myPageProfileEditViewModel: MyPageProfileEditViewModel
    private lateinit var userMapper: UserMapper

    @Before
    fun init() {
        userMapper = UserMapper()
        myPageProfileEditViewModel =
            MyPageProfileEditViewModel(getUserProfileUseCase, editUserProfileUseCase, userMapper)
    }

    @Test
    fun `유저 정보가 전부 입력되지 않았을 때 테스트`() {
        myPageProfileEditViewModel.run {
            userModel.value = UserModel(
                id = "jinwoo123",
                password = "jinwoo123!!!",
                username = "jinwoo",
                address = "강남구",
                imageFile = File(""),
                description = ""
            )

            clickComplete()

            createToastEvent.test().assertValue("전부 채워주세요")
        }
    }

    @Test
    fun `유저 정보가 전부 입력되었을 때 테스트`() {
        myPageProfileEditViewModel.run {
            userModel.value = UserModel(
                id = "jinwoo123",
                password = "jinwoo123!!!",
                username = "jinwoo",
                address = "강남구",
                imageFile = File(""),
                description = "안녕하세요"
            )

            clickComplete()

            createToastEvent.test().assertNoValue()
        }
    }

    @Test
    fun `이미지 가져오는 이벤트 테스트`() {
        myPageProfileEditViewModel.run {
            clickImageEdit()

            getProfileImageEvent.test().assertHasValue()
        }
    }

    @Test
    fun `유저정보 가져오는 행위 성공 알림 테스트`() {
        myPageProfileEditViewModel.run {
            val result = UserModel(id = "jinwoo")
            getSuccess(result)
            userModel.test().assertValue(result)
        }
    }

    @Test
    fun `유저정보 가져오는 행위 실패 알림 테스트`() {
        myPageProfileEditViewModel.run {
            val failMessage = "유저정보를 가져올 수 없습니다."
            getFail(failMessage)
            createToastEvent.test().assertValue(failMessage)
        }
    }

    @Test
    fun `유저정보 수정하는 행위 성공 알림 테스트`() {
        myPageProfileEditViewModel.run {
            editSuccess()
            createToastEvent.test().assertValue("수정이 완료되었습니다")
        }
    }

    @Test
    fun `유저정보 수정하는 행위 실패 알림 테스트`() {
        myPageProfileEditViewModel.run {
            val failMessage = "유저정보를 수정할 수 없습니다."
            editFail(failMessage)
            createToastEvent.test().assertValue(failMessage)
        }
    }
}
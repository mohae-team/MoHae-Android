package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.DisLikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.LikePlaceInfoUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.PlaceMapper
import com.mohaeyo.mohae.model.PlaceModel
import com.mohaeyo.mohae.viewmodel.main.place.PlaceSearchViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class PlaceSearchViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var getPlaceInfoUseCase: GetPlaceInfoUseCase
    @Mock
    private lateinit var likePlaceInfoUseCase: LikePlaceInfoUseCase
    @Mock
    private lateinit var disLikePlaceInfoUseCase: DisLikePlaceInfoUseCase

    private lateinit var placeSearchViewModel: PlaceSearchViewModel
    private lateinit var placeMapper: PlaceMapper

    @Before
    fun init() {
        placeMapper = PlaceMapper()
        placeSearchViewModel =
            PlaceSearchViewModel(
                getPlaceInfoUseCase, likePlaceInfoUseCase, disLikePlaceInfoUseCase, placeMapper)
    }

    @Test
    fun `장소정보 가져오는 행위 성공 알림 테스트`() {
        placeSearchViewModel.run {
            val result = PlaceModel(name = "코엑스")
            getSuccess(result)
            placeModel.test().assertValue(result)
        }
    }

    @Test
    fun `장소정보 가져오는 행위 실패 알림 테스트`() {
        placeSearchViewModel.run {
            val failMessage = "장소정보를 가져올 수 없습니다."
            getFail(failMessage)

            placeModel.test().assertValue(
                PlaceModel(
                    name = failMessage,
                    description = failMessage,
                    isLike = false,
                    likeCount = 0
            ))

            createToastEvent.test().assertValue(failMessage)
        }
    }

    @Test
    fun `장소정보 좋아요하는 행위 성공 알림 테스트`() {
        placeSearchViewModel.run {
            likeSuccess()
            createToastEvent.test().assertValue("좋아요가 반영되었습니다")
        }
    }

    @Test
    fun `장소정보 좋아요하는 행위 실패 알림 테스트`() {
        placeSearchViewModel.run {
            val failMessage = "장소정보를 좋아요할 수 없습니다."
            likeFail(failMessage)

            createToastEvent.test().assertValue(failMessage)
        }
    }

    @Test
    fun `장소정보 좋아요 취소하는 행위 성공 알림 테스트`() {
        placeSearchViewModel.run {
            disLikeSuccess()
            createToastEvent.test().assertValue("좋아요가 취소되었습니다")
        }
    }

    @Test
    fun `장소정보 좋아요 취소하는 행위 실패 알림 테스트`() {
        placeSearchViewModel.run {
            val failMessage = "장소정보 좋아요를 최소할 수 없습니다."

            disLikeFail(failMessage)
            createToastEvent.test().assertValue(failMessage)
        }
    }
}
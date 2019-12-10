package com.mohaeyo.mohae.viewmodel

import com.jraska.livedata.test
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.PostPlaceInfoUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.PlaceMapper
import com.mohaeyo.mohae.model.PlaceModel
import com.mohaeyo.mohae.viewmodel.main.place.PlaceDocViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class PlaceDocViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var postPlaceInfoUseCase: PostPlaceInfoUseCase
    @Mock
    private lateinit var getPlaceInfoUseCase: GetPlaceInfoUseCase

    private lateinit var placeDocViewModel: PlaceDocViewModel
    private lateinit var placeMapper: PlaceMapper

    @Before
    fun init() {
        placeMapper = PlaceMapper()
        placeDocViewModel =
            PlaceDocViewModel(getPlaceInfoUseCase, postPlaceInfoUseCase, placeMapper)
    }

    @Test
    fun `장소정보 가져오는 행위 성공 알림 테스트`() {
        placeDocViewModel.run {
            val result = PlaceModel(name = "코엑스")
            getSuccess(result)
            placeModel.test().assertValue(result)
        }
    }

    @Test
    fun `장소정보 가져오는 행위 실패 알림 테스트`() {
        placeDocViewModel.run {
            val failMessage = "장소정보를 가져올 수 없습니다."
            getFail(failMessage)
            createToastEvent.test().assertValue(failMessage)
        }
    }

    @Test
    fun `장소정보 추가하는 행위 성공 알림 테스트`() {
        placeDocViewModel.run {
            val result = PlaceModel(name = "코엑스")
            postSuccess(result)
            placeModel.test().assertValue(result)
            startDocToListEvent.test().assertHasValue()
        }
    }

    @Test
    fun `장소정보 추가하는 행위 실패 알림 테스트`() {
        placeDocViewModel.run {
            val failMessage = "장소정보를 가져올 수 없습니다."
            postFail(failMessage)
            createToastEvent.test().assertValue(failMessage)
        }
    }
}
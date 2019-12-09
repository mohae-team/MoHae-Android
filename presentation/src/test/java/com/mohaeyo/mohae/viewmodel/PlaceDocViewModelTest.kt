package com.mohaeyo.mohae.viewmodel

import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.PostPlaceInfoUseCase
import com.mohaeyo.mohae.BaseViewModelTest
import com.mohaeyo.mohae.mapper.PlaceMapper
import com.mohaeyo.mohae.viewmodel.main.place.PlaceDocViewModel
import org.junit.Before
import org.mockito.Mock

class PlaceDocViewModelTest: BaseViewModelTest() {
    @Mock
    private lateinit var createPlaceInfoUseCase: PostPlaceInfoUseCase
    @Mock
    private lateinit var getPlaceInfoUseCase: GetPlaceInfoUseCase

    private lateinit var placeDocViewModel: PlaceDocViewModel
    private lateinit var placeMapper: PlaceMapper

    @Before
    fun init() {
        placeMapper = PlaceMapper()
        placeDocViewModel =
            PlaceDocViewModel(getPlaceInfoUseCase, createPlaceInfoUseCase, placeMapper)
    }
}
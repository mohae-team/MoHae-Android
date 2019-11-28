package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.DisLikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.LikePlaceInfoUseCase
import com.mohaeyo.mohae.mapper.PlaceMapper

class PlaceSearchViewModelFactory(
    private val getPlaceInfoUseCase: GetPlaceInfoUseCase,
    private val likePlaceInfoUseCase: LikePlaceInfoUseCase,
    private val disLikePlaceInfoUseCase: DisLikePlaceInfoUseCase,
    private val placeMapper: PlaceMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetPlaceInfoUseCase::class.java,
        LikePlaceInfoUseCase::class.java,
        DisLikePlaceInfoUseCase::class.java,
        PlaceMapper::class.java
        ).newInstance(getPlaceInfoUseCase, likePlaceInfoUseCase, disLikePlaceInfoUseCase, placeMapper)
}
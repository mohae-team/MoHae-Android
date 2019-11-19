package com.mohaeyo.mohae.viewmodel.main.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.PostPlaceInfoUseCase
import com.mohaeyo.mohae.mapper.PlaceMapper

class PlaceDocViewModelFactory(
    private val getPlaceInfoUseCase: GetPlaceInfoUseCase,
    private val postPlaceInfoUseCase: PostPlaceInfoUseCase,
    private val placeMapper: PlaceMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(
        GetPlaceInfoUseCase::class.java,
        PostPlaceInfoUseCase::class.java,
        PlaceMapper::class.java).newInstance(getPlaceInfoUseCase, postPlaceInfoUseCase, placeMapper)
}
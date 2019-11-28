package com.mohaeyo.mohae.di.module.main.place

import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.PostPlaceInfoUseCase
import com.mohaeyo.mohae.di.scope.PlaceFragmentScope
import com.mohaeyo.mohae.mapper.PlaceMapper
import com.mohaeyo.mohae.viewmodel.main.place.PlaceDocViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PlaceDocModule {
    @PlaceFragmentScope
    @Provides
    fun provideViewModelFactory(getPlaceInfoUseCase: GetPlaceInfoUseCase,
                                postPlaceInfoUseCase: PostPlaceInfoUseCase,
                                placeMapper: PlaceMapper): PlaceDocViewModelFactory
            = PlaceDocViewModelFactory(getPlaceInfoUseCase, postPlaceInfoUseCase, placeMapper)
}
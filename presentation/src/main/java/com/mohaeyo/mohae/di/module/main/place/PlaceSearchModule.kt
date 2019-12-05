package com.mohaeyo.mohae.di.module.main.place

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.domain.usecase.DisLikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.LikePlaceInfoUseCase
import com.mohaeyo.mohae.di.scope.PlaceFragmentScope
import com.mohaeyo.mohae.mapper.PlaceMapper
import com.mohaeyo.mohae.ui.fragment.main.place.PlaceSearchFragment
import com.mohaeyo.mohae.viewmodel.main.place.PlaceSearchViewModel
import com.mohaeyo.mohae.viewmodel.main.place.PlaceSearchViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PlaceSearchModule {
    @PlaceFragmentScope
    @Provides
    fun provideViewModelFactory(getPlaceInfoUseCase: GetPlaceInfoUseCase,
                                likePlaceInfoUseCase: LikePlaceInfoUseCase,
                                disLikePlaceInfoUseCase: DisLikePlaceInfoUseCase,
                                placeMapper: PlaceMapper): PlaceSearchViewModelFactory
            = PlaceSearchViewModelFactory(getPlaceInfoUseCase, likePlaceInfoUseCase, disLikePlaceInfoUseCase, placeMapper)

    @PlaceFragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: PlaceSearchViewModelFactory,
        fragment: PlaceSearchFragment
    ): PlaceSearchViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(PlaceSearchViewModel::class.java)
}
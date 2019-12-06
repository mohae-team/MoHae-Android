package com.mohaeyo.mohae.di.module.main.place

import android.content.Context
import androidx.room.Room
import com.mohaeyo.data.datasource.PlaceDataSource
import com.mohaeyo.data.datasource.PlaceDataSourceImpl
import com.mohaeyo.data.handler.PlaceErrorHandlerImpl
import com.mohaeyo.data.local.database.PlaceDatabase
import com.mohaeyo.data.local.database.dao.PlaceDao
import com.mohaeyo.data.mapper.PlaceDataMapper
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.repository.PlaceRepositoryImpl
import com.mohaeyo.domain.handler.PlaceErrorHandler
import com.mohaeyo.domain.repository.PlaceRepository
import com.mohaeyo.domain.service.PlaceService
import com.mohaeyo.domain.service.PlaceServiceImpl
import com.mohaeyo.domain.usecase.DisLikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.LikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.PostPlaceInfoUseCase
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.mapper.PlaceMapper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class PlaceStaticModule {
    @MainFragmentScope
    @Provides
    fun provideLikePlaceInfoUseCase(placeService: PlaceService, composite: CompositeDisposable): LikePlaceInfoUseCase
            = LikePlaceInfoUseCase(placeService, composite)

    @MainFragmentScope
    @Provides
    fun provideDisLikePlaceInfoUseCase(placeService: PlaceService, composite: CompositeDisposable): DisLikePlaceInfoUseCase
            = DisLikePlaceInfoUseCase(placeService, composite)

    @MainFragmentScope
    @Provides
    fun providePostPlaceInfoUseCase(placeService: PlaceService, composite: CompositeDisposable): PostPlaceInfoUseCase
            = PostPlaceInfoUseCase(placeService, composite)

    @MainFragmentScope
    @Provides
    fun provideGetPlaceInfoUseCase(placeService: PlaceService, composite: CompositeDisposable): GetPlaceInfoUseCase
            = GetPlaceInfoUseCase(placeService, composite)

    @MainFragmentScope
    @Provides
    fun providePlaceService(placeRepository: PlaceRepository, placeErrorHandler: PlaceErrorHandler): PlaceService
            = PlaceServiceImpl(placeRepository, placeErrorHandler)

    @MainFragmentScope
    @Provides
    fun providePlaceErrorHandler(): PlaceErrorHandler
            = PlaceErrorHandlerImpl()

    @MainFragmentScope
    @Provides
    fun providePlaceRepository(placeDataSource: PlaceDataSource,
                               placeDataMapper: PlaceDataMapper): PlaceRepository
            = PlaceRepositoryImpl(placeDataSource, placeDataMapper)

    @MainFragmentScope
    @Provides
    fun providePlaceDataMapper(): PlaceDataMapper = PlaceDataMapper()

    @MainFragmentScope
    @Provides
    fun providePlaceMapper(): PlaceMapper = PlaceMapper()

    @MainFragmentScope
    @Provides
    fun providePlaceDataSource(api: Api, placeDao: PlaceDao): PlaceDataSource
            = PlaceDataSourceImpl(api, placeDao)

    @MainFragmentScope
    @Provides
    fun providePlaceDatabase(context: Context): PlaceDatabase
            = Room.databaseBuilder(context, PlaceDatabase::class.java, "place.db").build()

    @MainFragmentScope
    @Provides
    fun providePlaceDao(database: PlaceDatabase): PlaceDao
            = database.placeDao
}
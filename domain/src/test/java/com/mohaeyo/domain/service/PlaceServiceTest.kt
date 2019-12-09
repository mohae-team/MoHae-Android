package com.mohaeyo.domain.service

import com.mohaeyo.domain.BaseTest
import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.handler.PlaceErrorHandler
import com.mohaeyo.domain.repository.PlaceRepository
import com.mohaeyo.domain.usecase.DisLikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.GetPlaceInfoUseCase
import com.mohaeyo.domain.usecase.LikePlaceInfoUseCase
import com.mohaeyo.domain.usecase.PostPlaceInfoUseCase
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*

class PlaceServiceTest: BaseTest() {
    @Mock
    private lateinit var placeRepository: PlaceRepository
    @Mock
    private lateinit var placeErrorHandler: PlaceErrorHandler

    private lateinit var placeService: PlaceService

    private lateinit var getPlaceInfoUseCase: GetPlaceInfoUseCase
    private lateinit var postPlaceInfoUseCase: PostPlaceInfoUseCase
    private lateinit var likePlaceInfoUseCase: LikePlaceInfoUseCase
    private lateinit var disLikePlaceInfoUseCase: DisLikePlaceInfoUseCase

    @Before
    fun init() {
        placeService = PlaceServiceImpl(placeRepository, placeErrorHandler)

        getPlaceInfoUseCase = GetPlaceInfoUseCase(placeService, CompositeDisposable())
        postPlaceInfoUseCase = PostPlaceInfoUseCase(placeService, CompositeDisposable())
        likePlaceInfoUseCase = LikePlaceInfoUseCase(placeService, CompositeDisposable())
        disLikePlaceInfoUseCase = DisLikePlaceInfoUseCase(placeService, CompositeDisposable())
    }

    @Test
    fun `장소정보 가져오기 성공 테스트`() {
        `when`(placeRepository.getRemotePlace(ArgumentMatchers.anyString()))
            .thenReturn(Flowable.just(PlaceEntity()))

        getPlaceInfoUseCase.createFlowable(
            ArgumentMatchers.anyString()
        ).test().assertValue(PlaceEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `장소정보 가져오기 실패 테스트`() {
        val throwable = Throwable()

        `when`(placeRepository.getRemotePlace(ArgumentMatchers.anyString()))
            .thenReturn(Flowable.error(throwable))

        `when`(placeRepository.getLocalPlace())
            .thenReturn(PlaceEntity())

        doNothing().
            `when`(placeRepository).saveLocalPlace(PlaceEntity())

        `when`(placeErrorHandler.getInfoErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        getPlaceInfoUseCase.createFlowable(
            ArgumentMatchers.anyString()
        ).test().assertValue(PlaceEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `장소정보 추가하기 성공 테스트`() {
        `when`(placeRepository.postRemotePlace(PlaceEntity()))
            .thenReturn(Flowable.just(PlaceEntity()))

        postPlaceInfoUseCase.createFlowable(
            PlaceEntity()
        ).test().assertValue(PlaceEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `장소정보 추가하기 실패 테스트`() {
        val throwable = Throwable()

        `when`(placeRepository.postRemotePlace(PlaceEntity()))
            .thenReturn(Flowable.error(throwable))

        `when`(placeErrorHandler.postInfoErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        postPlaceInfoUseCase.createFlowable(
            PlaceEntity()
        ).test().assertValue(PlaceEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `장소정보 좋아요 하기 성공 테스트`() {
        `when`(placeRepository.postLikeRemotePlace(ArgumentMatchers.anyString()))
            .thenReturn(Flowable.just(PlaceEntity()))

        likePlaceInfoUseCase.createFlowable(
            ArgumentMatchers.anyString()
        ).test().assertValue(PlaceEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `장소정보 좋아요 하기 실패 테스트`() {
        val throwable = Throwable()

        `when`(placeRepository.postLikeRemotePlace(ArgumentMatchers.anyString()))
            .thenReturn(Flowable.error(throwable))

        `when`(placeRepository.getLocalPlace())
            .thenReturn(PlaceEntity())

        `when`(placeErrorHandler.postLikeErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        likePlaceInfoUseCase.createFlowable(
            ArgumentMatchers.anyString()
        ).test().assertValue(PlaceEntity() to ErrorHandlerEntity(isSuccess = false))
    }

    @Test
    fun `장소정보 좋아요 취소하기 성공 테스트`() {
        `when`(placeRepository.postDisLikeRemotePlace(ArgumentMatchers.anyString()))
            .thenReturn(Flowable.just(PlaceEntity()))

        disLikePlaceInfoUseCase.createFlowable(
            ArgumentMatchers.anyString()
        ).test().assertValue(PlaceEntity() to ErrorHandlerEntity(isSuccess = true))
    }

    @Test
    fun `장소정보 좋아요 취소하기 실패 테스트`() {
        val throwable = Throwable()

        `when`(placeRepository.postDisLikeRemotePlace(ArgumentMatchers.anyString()))
            .thenReturn(Flowable.error(throwable))

        `when`(placeRepository.getLocalPlace())
            .thenReturn(PlaceEntity())

        `when`(placeErrorHandler.postDisLikeErrorHandle(throwable))
            .thenReturn(ErrorHandlerEntity(isSuccess = false))

        disLikePlaceInfoUseCase.createFlowable(
            ArgumentMatchers.anyString()
        ).test().assertValue(PlaceEntity() to ErrorHandlerEntity(isSuccess = false))
    }
}
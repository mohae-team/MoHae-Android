package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.PlaceEntity
import com.mohaeyo.domain.service.PlaceService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class DisLikePlaceInfoUseCase(
    private val placeService: PlaceService,
    composite: CompositeDisposable
): UseCase<Pair<PlaceEntity, ErrorHandlerEntity>, String>(composite) {
    override fun createFlowable(location: String): Flowable<Pair<PlaceEntity, ErrorHandlerEntity>>
            = placeService.postDisLikePlace(location)
}
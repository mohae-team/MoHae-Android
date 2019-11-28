package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.service.FeedbackService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class GetFeedbackDetailUseCase(
    private val groupService: FeedbackService,
    composite: CompositeDisposable
): UseCase<Pair<FeedbackEntity, ErrorHandlerEntity>, Int>(composite) {
    override fun createFlowable(id: Int): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = groupService.getFeedbackDetail(id)
}
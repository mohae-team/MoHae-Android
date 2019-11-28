package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.service.FeedbackService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class GetFeedbackListUseCase(
    private val feedbackService: FeedbackService,
    composite: CompositeDisposable
): UseCase<Pair<List<FeedbackEntity>, ErrorHandlerEntity>, Unit>(composite) {
    override fun createFlowable(data: Unit): Flowable<Pair<List<FeedbackEntity>, ErrorHandlerEntity>>
            = feedbackService.getListFeedback()
}
package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.FeedbackEntity
import com.mohaeyo.domain.service.FeedbackService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class CreateFeedbackUseCase(
    private val feedbackService: FeedbackService,
    composite: CompositeDisposable
): UseCase<Pair<FeedbackEntity, ErrorHandlerEntity>, FeedbackEntity>(composite) {
    override fun createFlowable(feedback: FeedbackEntity): Flowable<Pair<FeedbackEntity, ErrorHandlerEntity>>
            = feedbackService.createFeedback(feedback)
}
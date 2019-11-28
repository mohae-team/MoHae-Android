package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.service.QAService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class CreateAnswerUseCase(
    private val qaService: QAService,
    composite: CompositeDisposable
): UseCase<Pair<AnswerEntity, ErrorHandlerEntity>, AnswerEntity>(composite) {
    override fun createFlowable(answer: AnswerEntity): Flowable<Pair<AnswerEntity, ErrorHandlerEntity>>
            = qaService.createAnswer(answer)
}
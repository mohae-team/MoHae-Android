package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.AnswerEntity
import com.mohaeyo.domain.service.QAService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class GetAnswerListUseCase(
    private val qaService: QAService,
    composite: CompositeDisposable
): UseCase<Pair<List<AnswerEntity>, ErrorHandlerEntity>, Int>(composite) {
    override fun createFlowable(questionId: Int): Flowable<Pair<List<AnswerEntity>, ErrorHandlerEntity>>
            = qaService.getAnswerList(questionId)
}
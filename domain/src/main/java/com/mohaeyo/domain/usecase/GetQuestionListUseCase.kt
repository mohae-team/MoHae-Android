package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.service.QAService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class GetQuestionListUseCase(
    private val qaService: QAService,
    composite: CompositeDisposable
): UseCase<Pair<List<QuestionEntity>, ErrorHandlerEntity>, Unit>(composite) {
    override fun createFlowable(data: Unit): Flowable<Pair<List<QuestionEntity>, ErrorHandlerEntity>>
            = qaService.getQuestionList()
}
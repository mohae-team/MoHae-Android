package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.QuestionEntity
import com.mohaeyo.domain.service.QAService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class CreateQuestionUseCase(
    private val qaService: QAService,
    composite: CompositeDisposable
): UseCase<Pair<QuestionEntity, ErrorHandlerEntity>, QuestionEntity>(composite) {
    override fun createFlowable(question: QuestionEntity): Flowable<Pair<QuestionEntity, ErrorHandlerEntity>>
            = qaService.createQuestion(question)
}
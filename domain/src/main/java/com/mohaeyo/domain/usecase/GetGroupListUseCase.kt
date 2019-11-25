package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.service.GroupService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class GetGroupListUseCase(
    private val groupService: GroupService,
    composite: CompositeDisposable
): UseCase<Pair<List<GroupEntity>, ErrorHandlerEntity>, Unit>(composite) {
    override fun createFlowable(data: Unit): Flowable<Pair<List<GroupEntity>, ErrorHandlerEntity>>
            = groupService.getListGroup()
}
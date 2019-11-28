package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.GroupEntity
import com.mohaeyo.domain.service.GroupService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class JoinGroupUseCase(
    private val groupService: GroupService,
    composite: CompositeDisposable
): UseCase<Pair<GroupEntity, ErrorHandlerEntity>, Int>(composite) {
    override fun createFlowable(id: Int): Flowable<Pair<GroupEntity, ErrorHandlerEntity>>
            = groupService.joinGroup(id)
}
package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.service.UserService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class GetUserProfileUseCase(
    private val service: UserService,
    composite: CompositeDisposable) : UseCase<Pair<UserEntity, ErrorHandlerEntity>, Unit>(composite) {

    override fun createFlowable(data: Unit): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
            = service.getUserProfile()
}
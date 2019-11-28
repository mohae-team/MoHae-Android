package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.service.UserService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class EditUserProfileUseCase(
    private val service: UserService,
    composite: CompositeDisposable) : UseCase<Pair<UserEntity, ErrorHandlerEntity>, UserEntity>(composite) {

    override fun createFlowable(user: UserEntity): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
            = service.editUserProfile(user)
}
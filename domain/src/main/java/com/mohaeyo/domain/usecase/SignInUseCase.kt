package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.service.AuthService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class SignInUseCase(
    private val service: AuthService,
    composite: CompositeDisposable): UseCase<Pair<TokenEntity, ErrorHandlerEntity>, AuthEntity>(composite) {

    override fun createFlowable(auth: AuthEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
            = service.signIn(auth)
}
package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.AuthEntity
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.service.AuthService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class SignInUseCase(val service: AuthService, composite: CompositeDisposable): UseCase<TokenEntity, AuthEntity>(composite) {
    override fun createFlowable(auth: AuthEntity): Flowable<TokenEntity>
            = service.signIn(auth)
}
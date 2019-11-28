package com.mohaeyo.domain.usecase

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.base.UseCase
import com.mohaeyo.domain.entity.TokenEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.service.AuthService
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class SignUpUseCase(val service: AuthService,
                    composite: CompositeDisposable): UseCase<Pair<TokenEntity, ErrorHandlerEntity>, UserEntity>(composite) {
    override fun createFlowable(user: UserEntity): Flowable<Pair<TokenEntity, ErrorHandlerEntity>>
            = service.signUp(user)
}
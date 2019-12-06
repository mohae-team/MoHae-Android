package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.handler.UserErrorHandler
import com.mohaeyo.domain.repository.UserRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userErrorHandler: UserErrorHandler): UserService {
    override fun editUserProfile(user: UserEntity): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
            = userRepository.postRemoteUser(user).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        user to userErrorHandler.editInfoErrorHandle(it)
    }

    override fun getUserProfile(): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
            = userRepository.getRemoteUser().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        userRepository.saveLocalUser(it.first)
    }.onErrorReturn {
        userRepository.getLocalUser() to userErrorHandler.getInfoErrorHandle(it)
    }
}
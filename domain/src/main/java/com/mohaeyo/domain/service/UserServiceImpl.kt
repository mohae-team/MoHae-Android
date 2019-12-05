package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.repository.UserRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class UserServiceImpl(private val userRepository: UserRepository): UserService {
    override fun editUserProfile(user: UserEntity): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
            = userRepository.postRemoteUser(user).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        user to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 유저 정보입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            else -> ErrorHandlerEntity()
        }
    }.timeout {
        Flowable.just(it to ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다"))
    }

    override fun getUserProfile(): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
            = userRepository.getRemoteUser().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        userRepository.saveLocalUser(it.first)
    }.onErrorReturn {
        userRepository.getLocalUser() to when (it) {
            is HttpException -> when(it.code()) {
                200 -> ErrorHandlerEntity(isSuccess = true)
                404 -> ErrorHandlerEntity(message = "존재하지 않는 유저 정보입니다")
                403 -> ErrorHandlerEntity(message = "권한이 없습니다")
                500 -> ErrorHandlerEntity(message = "서버 에러가 발생했습니다")
                else -> ErrorHandlerEntity(message = "알 수 없는 오류가 발생했습니다")
            }
            is ConnectException -> ErrorHandlerEntity(message = "인터넷 연결이 되지 않았습니다")
            is SocketTimeoutException -> ErrorHandlerEntity(message = "인터넷 연결이 불안정합니다")
            else -> ErrorHandlerEntity()
        }
    }
}
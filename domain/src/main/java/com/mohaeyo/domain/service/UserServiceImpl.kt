package com.mohaeyo.domain.service

import com.mohaeyo.domain.base.ErrorHandlerEntity
import com.mohaeyo.domain.entity.UserEntity
import com.mohaeyo.domain.repository.AuthRepository
import com.mohaeyo.domain.repository.UserRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class UserServiceImpl(private val userRepository: UserRepository): UserService {
    override fun editUserProfile(user: UserEntity): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
            = userRepository.postRemoteUser(user).map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.onErrorReturn {
        if (it is HttpException)
            userRepository.getLocalUser() to ErrorHandlerEntity(message = when(it.code()) {
                404 -> "존재하지 않는 유저 정보입니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else userRepository.getLocalUser() to ErrorHandlerEntity(isSuccess = true)
    }

    override fun getUserProfile(): Flowable<Pair<UserEntity, ErrorHandlerEntity>>
            = userRepository.getRemoteUser().map {
        it to ErrorHandlerEntity(isSuccess = true)
    }.doOnNext {
        userRepository.saveLocalUser(it.first)
    }.onErrorReturn {
        if (it is HttpException)
            userRepository.getLocalUser() to ErrorHandlerEntity(message = when(it.code()) {
                404 -> "존재하지 않는 유저 정보입니다"
                else -> "네트워크 상태를 확인해주세요"
            }, isSuccess = false)
        else userRepository.getLocalUser() to ErrorHandlerEntity(isSuccess = true)
    }
}
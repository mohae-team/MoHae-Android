package com.mohaeyo.mohae.di.module.main.mypage

import android.content.Context
import androidx.room.Room
import com.mohaeyo.data.datasource.UserDataSource
import com.mohaeyo.data.datasource.UserDataSourceImpl
import com.mohaeyo.data.handler.UserErrorHandlerImpl
import com.mohaeyo.data.local.database.UserDatabase
import com.mohaeyo.data.local.database.dao.UserDao
import com.mohaeyo.data.mapper.UserDataMapper
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.repository.UserRepositoryImpl
import com.mohaeyo.domain.handler.UserErrorHandler
import com.mohaeyo.domain.repository.UserRepository
import com.mohaeyo.domain.service.UserService
import com.mohaeyo.domain.service.UserServiceImpl
import com.mohaeyo.domain.usecase.EditUserProfileUseCase
import com.mohaeyo.domain.usecase.GetUserProfileUseCase
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.mapper.UserMapper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MyPageStaticModule {
    @MainFragmentScope
    @Provides
    fun provideEditUserProfileUseCase(userService: UserService, composite: CompositeDisposable): EditUserProfileUseCase
            = EditUserProfileUseCase(userService, composite)

    @MainFragmentScope
    @Provides
    fun provideGetUserProfileUseCase(userService: UserService, composite: CompositeDisposable): GetUserProfileUseCase
            = GetUserProfileUseCase(userService, composite)

    @MainFragmentScope
    @Provides
    fun provideUserService(userRepository: UserRepository, userErrorHandler: UserErrorHandler): UserService
            = UserServiceImpl(userRepository, userErrorHandler)

    @MainFragmentScope
    @Provides
    fun provideUserErrorHandler(): UserErrorHandler
            = UserErrorHandlerImpl()

    @MainFragmentScope
    @Provides
    fun provideUserRepository(userDataSource: UserDataSource,
                              userDataMapper: UserDataMapper
    ): UserRepository
            = UserRepositoryImpl(userDataSource, userDataMapper)

    @MainFragmentScope
    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()

    @MainFragmentScope
    @Provides
    fun provideUserDataMapper(): UserDataMapper = UserDataMapper()

    @MainFragmentScope
    @Provides
    fun provideUserDataSource(api: Api, userDao: UserDao): UserDataSource
            = UserDataSourceImpl(api, userDao)

    @MainFragmentScope
    @Provides
    fun provideUserDatabase(context: Context): UserDatabase
            = Room.databaseBuilder(context, UserDatabase::class.java, "user.db").build()

    @MainFragmentScope
    @Provides
    fun provideUserDao(database: UserDatabase): UserDao
            = database.userDao
}
package com.mohaeyo.mohae.di.module.signin

import com.mohaeyo.data.datasource.AuthDataSource
import com.mohaeyo.data.datasource.AuthDataSourceImpl
import com.mohaeyo.data.local.pref.LocalStorage
import com.mohaeyo.data.mapper.AuthDataMapper
import com.mohaeyo.data.mapper.UserDataMapper
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.repository.AuthRepositoryImpl
import com.mohaeyo.domain.repository.AuthRepository
import com.mohaeyo.domain.service.AuthService
import com.mohaeyo.domain.service.AuthServiceImpl
import com.mohaeyo.domain.usecase.SignInUseCase
import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.viewmodel.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SignInModule {
    @FragmentScope
    @Provides
    fun provideViewModelFactory(signInUseCase: SignInUseCase): SignInViewModelFactory
            = SignInViewModelFactory(signInUseCase)

    @FragmentScope
    @Provides
    fun provideSignInUseCase(authService: AuthService, composite: CompositeDisposable): SignInUseCase
            = SignInUseCase(authService, composite)

    @FragmentScope
    @Provides
    fun provideAuthService(authRepository: AuthRepository): AuthService
            = AuthServiceImpl(authRepository)

    @FragmentScope
    @Provides
    fun provideAuthRepository(authDataMapper: AuthDataMapper,
                              authDataSource: AuthDataSource,
                              userDataMapper: UserDataMapper): AuthRepository
            = AuthRepositoryImpl(authDataSource, authDataMapper, userDataMapper)

    @FragmentScope
    @Provides
    fun provideUserDataMapper(): UserDataMapper = UserDataMapper()

    @FragmentScope
    @Provides
    fun provideAuthDataMapper(): AuthDataMapper = AuthDataMapper()

    @FragmentScope
    @Provides
    fun provideAuthDataSource(api: Api, storage: LocalStorage): AuthDataSource
            = AuthDataSourceImpl(api, storage)
}
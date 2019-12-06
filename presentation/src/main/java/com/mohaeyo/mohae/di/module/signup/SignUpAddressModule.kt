package com.mohaeyo.mohae.di.module.signup

import androidx.lifecycle.ViewModelProviders
import com.mohaeyo.data.datasource.AuthDataSource
import com.mohaeyo.data.datasource.AuthDataSourceImpl
import com.mohaeyo.data.handler.AuthErrorHandlerImpl
import com.mohaeyo.data.local.pref.LocalStorage
import com.mohaeyo.data.mapper.AuthDataMapper
import com.mohaeyo.data.mapper.TokenDataMapper
import com.mohaeyo.data.mapper.UserDataMapper
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.repository.AuthRepositoryImpl
import com.mohaeyo.domain.handler.AuthErrorHandler
import com.mohaeyo.domain.repository.AuthRepository
import com.mohaeyo.domain.service.AuthService
import com.mohaeyo.domain.service.AuthServiceImpl
import com.mohaeyo.domain.usecase.SignInUseCase
import com.mohaeyo.domain.usecase.SignUpUseCase
import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.mapper.UserMapper
import com.mohaeyo.mohae.ui.fragment.signup.SignUpAddressFragment
import com.mohaeyo.mohae.ui.fragment.signup.SignUpFragment
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModel
import com.mohaeyo.mohae.viewmodel.signup.SignUpViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SignUpAddressModule {
    @FragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: SignUpViewModelFactory,
        fragment: SignUpAddressFragment
    ): SignUpViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(SignUpViewModel::class.java)

    @FragmentScope
    @Provides
    fun provideViewModelFactory(signUpUseCase: SignUpUseCase, userMapper: UserMapper): SignUpViewModelFactory
            = SignUpViewModelFactory(signUpUseCase, userMapper)

    @FragmentScope
    @Provides
    fun provideSignUpUseCase(authService: AuthService, composite: CompositeDisposable): SignUpUseCase
            = SignUpUseCase(authService, composite)

    @FragmentScope
    @Provides
    fun provideAuthService(authRepository: AuthRepository, authErrorHandler: AuthErrorHandler): AuthService
            = AuthServiceImpl(authRepository, authErrorHandler)

    @FragmentScope
    @Provides
    fun provideAuthErrorHandler(): AuthErrorHandler
            = AuthErrorHandlerImpl()

    @FragmentScope
    @Provides
    fun provideAuthRepository(authDataMapper: AuthDataMapper,
                              authDataSource: AuthDataSource,
                              userDataMapper: UserDataMapper,
                              tokenDataMapper: TokenDataMapper): AuthRepository
            = AuthRepositoryImpl(authDataSource, authDataMapper, tokenDataMapper, userDataMapper)

    @FragmentScope
    @Provides
    fun provideUserDataMapper(): UserDataMapper = UserDataMapper()

    @FragmentScope
    @Provides
    fun provideAuthDataMapper(): AuthDataMapper = AuthDataMapper()

    @FragmentScope
    @Provides
    fun provideTokenDataMapper(): TokenDataMapper = TokenDataMapper()

    @FragmentScope
    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()

    @FragmentScope
    @Provides
    fun provideAuthDataSource(api: Api, storage: LocalStorage): AuthDataSource
            = AuthDataSourceImpl(api, storage)
}
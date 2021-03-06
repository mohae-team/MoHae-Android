package com.mohaeyo.mohae.di.module.signin

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
import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.mapper.AuthMapper
import com.mohaeyo.mohae.ui.fragment.signin.SignInFragment
import com.mohaeyo.mohae.viewmodel.signin.SignInViewModel
import com.mohaeyo.mohae.viewmodel.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SignInModule {
    @FragmentScope
    @Provides
    fun provideViewModel(
        viewModelFactory: SignInViewModelFactory,
        fragment: SignInFragment
    ): SignInViewModel
            = ViewModelProviders.of(fragment, viewModelFactory).get(SignInViewModel::class.java)

    @FragmentScope
    @Provides
    fun provideViewModelFactory(signInUseCase: SignInUseCase, authMapper: AuthMapper): SignInViewModelFactory
            = SignInViewModelFactory(signInUseCase, authMapper)

    @FragmentScope
    @Provides
    fun provideSignInUseCase(authService: AuthService, composite: CompositeDisposable): SignInUseCase
            = SignInUseCase(authService, composite)

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
    fun provideAuthMapper(): AuthMapper = AuthMapper()

    @FragmentScope
    @Provides
    fun provideAuthDataSource(api: Api, storage: LocalStorage): AuthDataSource
            = AuthDataSourceImpl(api, storage)
}
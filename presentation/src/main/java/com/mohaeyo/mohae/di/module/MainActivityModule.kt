package com.mohaeyo.mohae.di.module

import com.mohaeyo.mohae.di.module.signin.SignInModule
import com.mohaeyo.mohae.di.module.main.MainModule
import com.mohaeyo.mohae.di.module.main.MainStaticModule
import com.mohaeyo.mohae.di.module.signup.SignUpModule
import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.ui.fragment.signin.SignInFragment
import com.mohaeyo.mohae.ui.fragment.main.MainFragment
import com.mohaeyo.mohae.ui.fragment.signup.SignUpAddressFragment
import com.mohaeyo.mohae.ui.fragment.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainStaticModule::class])
    abstract fun mainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SignInModule::class])
    abstract fun signinFragment(): SignInFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SignUpModule::class])
    abstract fun signupFragment(): SignUpFragment

    @FragmentScope
    @ContributesAndroidInjector(modules =  [SignUpModule::class])
    abstract fun signupAddressFragment(): SignUpAddressFragment
}
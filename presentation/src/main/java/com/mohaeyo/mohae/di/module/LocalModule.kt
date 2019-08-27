package com.mohaeyo.mohae.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.Context
import com.mohaeyo.data.local.pref.LocalStorage
import com.mohaeyo.data.local.pref.SharedPrefStorage


@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideLocalStorage(context: Context) : LocalStorage = SharedPrefStorage(context)
}
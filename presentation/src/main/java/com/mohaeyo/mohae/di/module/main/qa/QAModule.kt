package com.mohaeyo.mohae.di.module.main.qa

import com.mohaeyo.mohae.di.scope.FragmentScope
import com.mohaeyo.mohae.viewmodel.main.qa.QAViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class QAModule {
    @FragmentScope
    @Provides
    fun provideViewModelFactory(): QAViewModelFactory
            = QAViewModelFactory()
}
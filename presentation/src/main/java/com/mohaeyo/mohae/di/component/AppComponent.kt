package com.mohaeyo.mohae.di.component

import com.mohaeyo.mohae.di.module.ActivityModule
import com.mohaeyo.mohae.di.module.AppModule
import com.mohaeyo.mohae.di.app.BaseApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (ActivityModule::class)]
)
interface AppComponent: AndroidInjector<BaseApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApp): Builder
        fun build(): AppComponent
    }
}
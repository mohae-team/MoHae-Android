package com.mohaeyo.mohae.di.app

import androidx.preference.PreferenceManager
import com.mohaeyo.mohae.di.component.DaggerAppComponent
import com.mohaeyo.mohae.util.ThemeHelper
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref = sharedPreferences.getString("themePref", ThemeHelper.DEFAULT_MODE)!!
        ThemeHelper.applyTheme(themePref)
    }
}
package com.mohaeyo.mohae.di.module.main.group

import com.mohaeyo.mohae.di.scope.GroupFragmentScope
import com.mohaeyo.mohae.ui.fragment.main.group.GroupDetailFragment
import com.mohaeyo.mohae.ui.fragment.main.group.GroupDocFragment
import com.mohaeyo.mohae.ui.fragment.main.group.GroupListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GroupModule {

    @GroupFragmentScope
    @ContributesAndroidInjector(modules = [GroupListModule::class])
    abstract fun groupListFragment(): GroupListFragment

    @GroupFragmentScope
    @ContributesAndroidInjector(modules = [GroupDocModule::class])
    abstract fun groupDocFragment(): GroupDocFragment

    @GroupFragmentScope
    @ContributesAndroidInjector(modules = [GroupDetailModule::class])
    abstract fun groupDetailFragment(): GroupDetailFragment
}
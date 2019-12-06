package com.mohaeyo.mohae.di.module.main.group

import android.content.Context
import androidx.room.Room
import com.mohaeyo.data.datasource.GroupDataSource
import com.mohaeyo.data.datasource.GroupDataSourceImpl
import com.mohaeyo.data.handler.GroupErrorHandlerImpl
import com.mohaeyo.data.local.database.GroupDatabase
import com.mohaeyo.data.local.database.dao.GroupDao
import com.mohaeyo.data.mapper.GroupDataMapper
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.repository.GroupRepositoryImpl
import com.mohaeyo.domain.handler.GroupErrorHandler
import com.mohaeyo.domain.repository.GroupRepository
import com.mohaeyo.domain.service.GroupService
import com.mohaeyo.domain.service.GroupServiceImpl
import com.mohaeyo.domain.usecase.*
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.mapper.GroupMapper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class GroupStaticModule {
    @MainFragmentScope
    @Provides
    fun provideGetGroupListUseCase(groupService: GroupService, composite: CompositeDisposable): GetGroupListUseCase
            = GetGroupListUseCase(groupService, composite)

    @MainFragmentScope
    @Provides
    fun provideGetGroupDetailUseCase(groupService: GroupService, composite: CompositeDisposable): GetGroupDetailUseCase
            = GetGroupDetailUseCase(groupService, composite)

    @MainFragmentScope
    @Provides
    fun provideCreateGroupUseCase(groupService: GroupService, composite: CompositeDisposable): CreateGroupUseCase
            = CreateGroupUseCase(groupService, composite)

    @MainFragmentScope
    @Provides
    fun provideJoinGroupUseCase(groupService: GroupService, composite: CompositeDisposable): JoinGroupUseCase
            = JoinGroupUseCase(groupService, composite)

    @MainFragmentScope
    @Provides
    fun provideCancelJoinGroupUseCase(groupService: GroupService, composite: CompositeDisposable): CancelJoinGroupUseCase
            = CancelJoinGroupUseCase(groupService, composite)

    @MainFragmentScope
    @Provides
    fun provideGroupService(groupRepository: GroupRepository, groupErrorHandler: GroupErrorHandler): GroupService
            = GroupServiceImpl(groupRepository, groupErrorHandler)

    @MainFragmentScope
    @Provides
    fun provideGroupErrorHandler(): GroupErrorHandler
            = GroupErrorHandlerImpl()

    @MainFragmentScope
    @Provides
    fun provideGroupRepository(groupDataSource: GroupDataSource,
                               groupDataMapper: GroupDataMapper
    ): GroupRepository
            = GroupRepositoryImpl(groupDataSource, groupDataMapper)

    @MainFragmentScope
    @Provides
    fun provideGroupMapper(): GroupMapper = GroupMapper()

    @MainFragmentScope
    @Provides
    fun provideGroupDataMapper(): GroupDataMapper = GroupDataMapper()

    @MainFragmentScope
    @Provides
    fun provideGroupDataSource(api: Api, userDao: GroupDao): GroupDataSource
            = GroupDataSourceImpl(api, userDao)

    @MainFragmentScope
    @Provides
    fun provideGroupDatabase(context: Context): GroupDatabase
            = Room.databaseBuilder(context, GroupDatabase::class.java, "group.db").build()

    @MainFragmentScope
    @Provides
    fun provideGroupDao(database: GroupDatabase): GroupDao
            = database.groupDao
}
package com.mohaeyo.mohae.di.module.main.feedback

import android.content.Context
import androidx.room.Room
import com.mohaeyo.data.datasource.FeedbackDataSource
import com.mohaeyo.data.datasource.FeedbackDataSourceImpl
import com.mohaeyo.data.handler.FeedbackErrorHandlerImpl
import com.mohaeyo.data.local.database.FeedbackDatabase
import com.mohaeyo.data.local.database.dao.FeedbackDao
import com.mohaeyo.data.mapper.FeedbackDataMapper
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.repository.FeedbackRepositoryImpl
import com.mohaeyo.domain.handler.FeedbackErrorHandler
import com.mohaeyo.domain.repository.FeedbackRepository
import com.mohaeyo.domain.service.FeedbackService
import com.mohaeyo.domain.service.FeedbackServiceImpl
import com.mohaeyo.domain.usecase.*
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.mapper.FeedbackMapper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FeedbackStaticModule {
    @MainFragmentScope
    @Provides
    fun provideGetFeedbackListUseCase(feedbackService: FeedbackService, composite: CompositeDisposable): GetFeedbackListUseCase
            = GetFeedbackListUseCase(feedbackService, composite)

    @MainFragmentScope
    @Provides
    fun provideGetFeedbackDetailUseCase(feedbackService: FeedbackService, composite: CompositeDisposable): GetFeedbackDetailUseCase
            = GetFeedbackDetailUseCase(feedbackService, composite)

    @MainFragmentScope
    @Provides
    fun provideCreateFeedbackUseCase(feedbackService: FeedbackService, composite: CompositeDisposable): CreateFeedbackUseCase
            = CreateFeedbackUseCase(feedbackService, composite)

    @MainFragmentScope
    @Provides
    fun provideLikeFeedbackUseCase(feedbackService: FeedbackService, composite: CompositeDisposable): LikeFeedbackUseCase
            = LikeFeedbackUseCase(feedbackService, composite)

    @MainFragmentScope
    @Provides
    fun provideHateFeedbackUseCase(feedbackService: FeedbackService, composite: CompositeDisposable): HateFeedbackUseCase
            = HateFeedbackUseCase(feedbackService, composite)

    @MainFragmentScope
    @Provides
    fun provideFeedbackService(feedbackRepository: FeedbackRepository, feedbackErrorHandler: FeedbackErrorHandler): FeedbackService
            = FeedbackServiceImpl(feedbackRepository, feedbackErrorHandler)

    @MainFragmentScope
    @Provides
    fun provideFeedbackErrorHandler(): FeedbackErrorHandler
            = FeedbackErrorHandlerImpl()

    @MainFragmentScope
    @Provides
    fun provideFeedbackRepository(feedbackDataSource: FeedbackDataSource,
                               feedbackDataMapper: FeedbackDataMapper
    ): FeedbackRepository
            = FeedbackRepositoryImpl(feedbackDataSource, feedbackDataMapper)

    @MainFragmentScope
    @Provides
    fun provideFeedbackMapper(): FeedbackMapper = FeedbackMapper()

    @MainFragmentScope
    @Provides
    fun provideFeedbackDataMapper(): FeedbackDataMapper = FeedbackDataMapper()

    @MainFragmentScope
    @Provides
    fun provideFeedbackDataSource(api: Api, userDao: FeedbackDao): FeedbackDataSource
            = FeedbackDataSourceImpl(api, userDao)

    @MainFragmentScope
    @Provides
    fun provideFeedbackDatabase(context: Context): FeedbackDatabase
            = Room.databaseBuilder(context, FeedbackDatabase::class.java, "feedback.db").build()

    @MainFragmentScope
    @Provides
    fun provideFeedbackDao(database: FeedbackDatabase): FeedbackDao
            = database.feedbackDao
}
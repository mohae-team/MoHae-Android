package com.mohaeyo.mohae.di.module.main.qa

import android.content.Context
import androidx.room.Room
import com.mohaeyo.data.datasource.QADataSource
import com.mohaeyo.data.datasource.QADataSourceImpl
import com.mohaeyo.data.local.database.AnswerDatabase
import com.mohaeyo.data.local.database.QuestionDatabase
import com.mohaeyo.data.local.database.dao.AnswerDao
import com.mohaeyo.data.local.database.dao.QuestionDao
import com.mohaeyo.data.mapper.AnswerDataMapper
import com.mohaeyo.data.mapper.QuestionDataMapper
import com.mohaeyo.data.remote.Api
import com.mohaeyo.data.repository.QARepositoryImpl
import com.mohaeyo.domain.repository.QARepository
import com.mohaeyo.domain.service.QAService
import com.mohaeyo.domain.service.QAServiceImpl
import com.mohaeyo.domain.usecase.*
import com.mohaeyo.mohae.di.scope.MainFragmentScope
import com.mohaeyo.mohae.mapper.AnswerMapper
import com.mohaeyo.mohae.mapper.QuestionMapper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class QAStaticModule {
    @MainFragmentScope
    @Provides
    fun provideCreateAnswerUseCase(qaService: QAService, composite: CompositeDisposable): CreateAnswerUseCase
            = CreateAnswerUseCase(qaService, composite)

    @MainFragmentScope
    @Provides
    fun provideCreateQuestionUseCase(qaService: QAService, composite: CompositeDisposable): CreateQuestionUseCase
            = CreateQuestionUseCase(qaService, composite)

    @MainFragmentScope
    @Provides
    fun provideGetQuestionDetailUseCase(qaService: QAService, composite: CompositeDisposable): GetQuestionDetailUseCase
            = GetQuestionDetailUseCase(qaService, composite)

    @MainFragmentScope
    @Provides
    fun provideGetAnswerListUseCase(qaService: QAService, composite: CompositeDisposable): GetAnswerListUseCase
            = GetAnswerListUseCase(qaService, composite)

    @MainFragmentScope
    @Provides
    fun provideGetQuestionListUseCase(qaService: QAService, composite: CompositeDisposable): GetQuestionListUseCase
            = GetQuestionListUseCase(qaService, composite)

    @MainFragmentScope
    @Provides
    fun provideQAService(qaRepository: QARepository): QAService
            = QAServiceImpl(qaRepository)

    @MainFragmentScope
    @Provides
    fun provideQARepository(qaDataSource: QADataSource,
                               questionDataMapper: QuestionDataMapper,
                               answerDataMapper: AnswerDataMapper): QARepository
            = QARepositoryImpl(qaDataSource, questionDataMapper, answerDataMapper)

    @MainFragmentScope
    @Provides
    fun provideQuestionDataMapper(): QuestionDataMapper = QuestionDataMapper()

    @MainFragmentScope
    @Provides
    fun provideQuestionMapper(): QuestionMapper = QuestionMapper()

    @MainFragmentScope
    @Provides
    fun provideAnswerDataMapper(): AnswerDataMapper = AnswerDataMapper()

    @MainFragmentScope
    @Provides
    fun provideAnswerMapper(): AnswerMapper = AnswerMapper()

    @MainFragmentScope
    @Provides
    fun provideQADataSource(api: Api, questionDao: QuestionDao, answerDao: AnswerDao): QADataSource
            = QADataSourceImpl(api, questionDao, answerDao)

    @MainFragmentScope
    @Provides
    fun provideQuestionDatabase(context: Context): QuestionDatabase
            = Room.databaseBuilder(context, QuestionDatabase::class.java, "question.db").build()

    @MainFragmentScope
    @Provides
    fun provideQuestionDao(database: QuestionDatabase): QuestionDao
            = database.questionDao

    @MainFragmentScope
    @Provides
    fun provideAnswerDatabase(context: Context): AnswerDatabase
            = Room.databaseBuilder(context, AnswerDatabase::class.java, "answer.db").build()

    @MainFragmentScope
    @Provides
    fun provideAnswerDao(database: AnswerDatabase): AnswerDao
            = database.answerDao
}
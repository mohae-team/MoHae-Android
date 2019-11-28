package com.mohaeyo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohaeyo.data.local.database.dao.QuestionDao
import com.mohaeyo.data.local.database.entity.Question

@Database(entities = [Question::class], version = 1)
abstract class QuestionDatabase: RoomDatabase() {
    abstract val questionDao: QuestionDao
}
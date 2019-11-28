package com.mohaeyo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohaeyo.data.local.database.dao.AnswerDao
import com.mohaeyo.data.local.database.entity.Answer

@Database(entities = [Answer::class], version = 1)
abstract class AnswerDatabase: RoomDatabase() {
    abstract val answerDao: AnswerDao
}
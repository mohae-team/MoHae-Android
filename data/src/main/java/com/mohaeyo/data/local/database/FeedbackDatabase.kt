package com.mohaeyo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohaeyo.data.local.database.dao.FeedbackDao
import com.mohaeyo.data.local.database.entity.Feedback

@Database(entities = [Feedback::class], version = 1)
abstract class FeedbackDatabase: RoomDatabase() {
    abstract val feedbackDao: FeedbackDao
}
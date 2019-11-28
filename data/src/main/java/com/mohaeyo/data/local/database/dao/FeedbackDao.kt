package com.mohaeyo.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohaeyo.data.local.database.entity.Feedback

@Dao
interface FeedbackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFeedback(vararg feedback: Feedback)

    @Query("SELECT * FROM `feedback` WHERE id = :id")
    fun getFeedback(id: Int): Feedback

    @Query("SELECT * FROM `feedback`")
    fun getFeedbackList(): List<Feedback>
}
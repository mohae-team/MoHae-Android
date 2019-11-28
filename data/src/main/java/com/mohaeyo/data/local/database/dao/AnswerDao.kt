package com.mohaeyo.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohaeyo.data.local.database.entity.Answer

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAnswer(vararg answer: Answer)

    @Query("SELECT * FROM answer WHERE questionId = :questionId")
    fun getAnswerList(questionId: Int): List<Answer>
}
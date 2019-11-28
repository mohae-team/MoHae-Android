package com.mohaeyo.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohaeyo.data.local.database.entity.Question

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveQuestion(vararg group: Question)

    @Query("SELECT * FROM question WHERE id = :id")
    fun getQuestion(id: Int): Question

    @Query("SELECT * FROM question")
    fun getQuestionList(): List<Question>
}
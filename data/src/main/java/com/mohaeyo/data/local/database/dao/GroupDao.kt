package com.mohaeyo.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohaeyo.data.local.database.entity.Group

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGroup(vararg group: Group)

    @Query("SELECT * FROM `group` WHERE id = :id")
    fun getGroup(id: Int): Group

    @Query("SELECT * FROM `group`")
    fun getGroupList(): List<Group>
}
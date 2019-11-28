package com.mohaeyo.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohaeyo.data.local.database.entity.Place

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePlace(vararg place: Place)

    @Query("SELECT * FROM place")
    fun getPlace(): Place
}
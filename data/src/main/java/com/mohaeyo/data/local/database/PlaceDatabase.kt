package com.mohaeyo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohaeyo.data.local.database.dao.PlaceDao
import com.mohaeyo.data.local.database.entity.Place

@Database(entities = [Place::class], version = 1)
abstract class PlaceDatabase: RoomDatabase() {
    abstract val placeDao: PlaceDao
}
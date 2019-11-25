package com.mohaeyo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohaeyo.data.local.database.dao.GroupDao
import com.mohaeyo.data.local.database.entity.Group

@Database(entities = [Group::class], version = 1)
abstract class GroupDatabase: RoomDatabase() {
    abstract val groupDao: GroupDao
}
package com.mohaeyo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohaeyo.data.local.database.dao.UserDao
import com.mohaeyo.data.local.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
        abstract val userDao: UserDao
}
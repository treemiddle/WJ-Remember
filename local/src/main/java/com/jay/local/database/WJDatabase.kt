package com.jay.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.local.dao.UserDao
import com.jay.local.model.LocalEntity

@Database(entities = [LocalEntity::class], exportSchema = false, version = 1)
abstract class WJDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
package com.example.telefonrehberi2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.telefonrehberi2.model.RehberModel

@Database(entities = [RehberModel::class], version = 1)
abstract class RehberDatabase : RoomDatabase() {
    abstract fun rehberDao() : RehberDao
}
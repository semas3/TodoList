package com.example.lab4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab4.TaskInfo

@Database(entities = [TaskInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskInfoDao(): TaskInfoDao
}
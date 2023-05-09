package com.example.lab4.database

import androidx.room.*
import com.example.lab4.TaskInfo

@Dao
interface TaskInfoDao {
    @Query("SELECT * FROM taskInfo")
    fun getAll(): List<TaskInfo>

    @Insert
    fun insertAll(vararg tasks: TaskInfo)

    @Update
    fun update(vararg tasks: TaskInfo)

    @Delete
    fun delete(tasks: TaskInfo)
}
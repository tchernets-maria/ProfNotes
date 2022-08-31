package ru.profsoft.profnotes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.profsoft.profnotes.data.model.entities.TaskEntity

@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(task: TaskEntity)

    @Update
    suspend fun upTask(task: TaskEntity)

    @Query("SELECT * FROM taskEntity")
    suspend fun getTasks(): List<TaskEntity>
}
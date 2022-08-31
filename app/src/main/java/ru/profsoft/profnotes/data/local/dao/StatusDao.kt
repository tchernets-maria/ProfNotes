package ru.profsoft.profnotes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.profsoft.profnotes.data.model.entities.StatusEntity

@Dao
interface StatusDao {
    @Insert
    suspend fun addStatus(note: StatusEntity)

    @Query("SELECT * FROM statusEntity")
    suspend fun getStatus(): List<StatusEntity>
}
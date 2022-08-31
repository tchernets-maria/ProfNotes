package ru.profsoft.profnotes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.profsoft.profnotes.data.model.entities.NoteEntity

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: NoteEntity)

    @Query("SELECT * FROM noteEntity")
    suspend fun getNotes(): List<NoteEntity>
}
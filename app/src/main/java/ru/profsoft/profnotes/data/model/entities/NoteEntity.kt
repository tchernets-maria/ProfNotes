package ru.profsoft.profnotes.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteEntity")
data class NoteEntity (
    val title: String,
    val description: String,
    val date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
package ru.profsoft.profnotes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.profsoft.profnotes.data.local.dao.NoteDao
import ru.profsoft.profnotes.data.local.dao.StatusDao
import ru.profsoft.profnotes.data.local.dao.TaskDao
import ru.profsoft.profnotes.data.local.db.LocalNoteAndTaskDb.Companion.DATABASE_VERSION
import ru.profsoft.profnotes.data.model.entities.NoteEntity
import ru.profsoft.profnotes.data.model.entities.StatusEntity
import ru.profsoft.profnotes.data.model.entities.TaskEntity

@Database(
    entities = [
        NoteEntity::class,
        StatusEntity::class,
        TaskEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class LocalNoteAndTaskDb: RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun statusDao(): StatusDao
    abstract fun taskDao(): TaskDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "LocalNoteAndTaskDb"
    }
}
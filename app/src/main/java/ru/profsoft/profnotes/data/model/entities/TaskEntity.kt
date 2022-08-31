package ru.profsoft.profnotes.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "taskEntity",
    foreignKeys = [ForeignKey(
        entity = StatusEntity::class,
        parentColumns = ["id"],
        childColumns = ["statusId"]
)])
data class TaskEntity (
    val title: String,
    val description: String,
    val date: String,
    @ColumnInfo(name = "statusId")
    val statusId: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
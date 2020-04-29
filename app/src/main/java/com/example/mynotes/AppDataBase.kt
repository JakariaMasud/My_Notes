package com.example.mynotes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotes.Model.Note

@Database(entities = [Note::class], version = 1,exportSchema = false)
abstract  class AppDataBase:RoomDatabase() {
    abstract  fun getNoteDao():NoteDao
}
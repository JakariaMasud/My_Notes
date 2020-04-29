package com.example.mynotes

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotes.Model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote( note:Note)
    @Update
    suspend fun updateNote(note:Note)
    @Delete
    suspend fun deleteNote(note:Note)
    @Query("SELECT * FROM note_table ORDER BY modificationTime DESC")
     fun observeAllNotes():LiveData<List<Note>>
    @Query("SELECT * FROM note_table")
    suspend fun getAllNotes():List<Note>
    @Query("DELETE  FROM note_table")
    suspend fun deleteAllNotes()
    @Query("SELECT * FROM note_table WHERE id =:id")
     fun observeNoteById(id:Int):LiveData<Note>
    @Query("SELECT * FROM note_table WHERE id =:id")
   suspend fun getNoteById(id:Int):Note
    @Query("Delete  FROM note_table WHERE id =:id")
    suspend fun deleteNoteById(id:Int)



}
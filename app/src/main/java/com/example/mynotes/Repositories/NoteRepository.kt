package com.example.mynotes.Repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.mynotes.Model.Note
import com.example.mynotes.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Result.Companion.success

class NoteRepository  @Inject constructor (private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note)= withContext(Dispatchers.IO){
        Log.e("insert","inserting")
        return@withContext noteDao.insertNote(note)

    }


    suspend  fun updateNote(note: Note)= withContext(Dispatchers.IO){
        noteDao.updateNote(note)

    }


    suspend fun getNoteById(id:Int)= noteDao.getNoteById(id)

    fun observeNoteById(id:Int)=noteDao.observeNoteById(id)


    suspend fun getAllNotes()= withContext(Dispatchers.IO){
         return@withContext noteDao.getAllNotes()

     }
    fun observeAllNotes()= noteDao.observeAllNotes()


    suspend fun deleteNote(note: Note)= withContext(Dispatchers.IO){
        noteDao.deleteNote(note)
    }



    suspend fun deleteAllNotes()= withContext(Dispatchers.IO){
        noteDao.deleteAllNotes()
    }

    suspend fun deleteNoteById(id:Int)= withContext(Dispatchers.IO){
        noteDao.deleteNoteById(id)

    }



}
package com.example.mynotes.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Model.Note
import com.example.mynotes.Repository.NoteRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class NoteViewModel @Inject constructor(private
    val repository: NoteRepository
) : ViewModel() {

    lateinit var note: Note

    fun insertNote(note: Note) {
        Log.e("insert","insertion start")
        GlobalScope.launch {
            repository.insertNote(note)
            Log.e("insert","insertion done")

        }
    }



    fun updateNote(note: Note) {
        GlobalScope.launch {
            repository.updateNote(note)
        }

    }

     fun getNoteById(id:Int):Note{
         return runBlocking {
             val result= async(Dispatchers.IO) {
                 return@async repository.getNoteById(id)
             }
            return@runBlocking result.await()
         }
    }



    fun observeNote(id:Int)= liveData {
        emitSource(repository.observeNoteById(id))
    }

    fun getAllNotes():List<Note>{
        return runBlocking {
            var result=async(Dispatchers.IO) {
                return@async repository.getAllNotes()
            }
            return@runBlocking result.await()
        }


    }


    fun observeAllNotes()= liveData {
        emitSource(repository.observeAllNotes())
    }


    fun deleteNoteById(id: Int)=
        GlobalScope.launch {
            repository.deleteNoteById(id)
        }



    fun deleteAllNotes()=
        GlobalScope.launch {
            repository.deleteAllNotes()
        }



    fun deleteNote(note:Note)=
        viewModelScope.launch {
            repository.deleteNote(note)
        }


    }


package com.example.mynotes.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    val title:String,
    val description:String,
    val modificationTime:Long
    ){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}

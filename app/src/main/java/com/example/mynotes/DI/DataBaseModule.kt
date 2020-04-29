package com.example.mynotes.DI

import android.app.Application
import androidx.room.Room
import com.example.mynotes.AppDataBase
import com.example.mynotes.NoteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDataBase(application: Application):AppDataBase{
        return Room.databaseBuilder(
            application.applicationContext,
            AppDataBase::class.java, "note_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideNoteDao(dataBase: AppDataBase):NoteDao{
        return dataBase.getNoteDao()
    }
}
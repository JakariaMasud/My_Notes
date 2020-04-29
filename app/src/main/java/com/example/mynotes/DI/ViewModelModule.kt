package com.example.mynotes.DI

import androidx.lifecycle.ViewModel
import com.example.mynotes.Repository.NoteRepository
import com.example.mynotes.ViewModel.NoteViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass



@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
     abstract fun bindMyViewModel(noteViewModel: NoteViewModel): ViewModel
}
package com.example.mynotes.DI

import com.example.mynotes.View.AddNoteFragment
import com.example.mynotes.View.EditNoteFragment
import com.example.mynotes.View.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[DataBaseModule::class,AppModule::class,ViewModelModule::class,ViewModelFactoryModule::class] )
interface ApplicationComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(addNoteFragment: AddNoteFragment)
    fun inject(editNoteFragment: EditNoteFragment)
}
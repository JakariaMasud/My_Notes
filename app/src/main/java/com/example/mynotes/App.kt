package com.example.mynotes

import android.app.Application
import com.example.mynotes.DI.AppModule
import com.example.mynotes.DI.ApplicationComponent
import com.example.mynotes.DI.DaggerApplicationComponent
import com.example.mynotes.DI.DataBaseModule


class App :Application() {
    lateinit var applicationComponent:ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .dataBaseModule(DataBaseModule())
            .build()
    }


}
package com.example.physicswallah
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        var INSTANCE: App = App()
    }


    override fun onCreate() {
        super.onCreate()

    }
    init {
        INSTANCE = this@App
    }



}
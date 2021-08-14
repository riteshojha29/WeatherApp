package com.ritesh.weatherapp.ui

import android.app.Application
import android.content.Context

class App: Application() {

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    fun getAppContext(): Context {
        return context
    }
}
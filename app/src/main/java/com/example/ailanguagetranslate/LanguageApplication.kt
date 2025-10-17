package com.example.ailanguagetranslate

import android.app.Application

class LanguageApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}

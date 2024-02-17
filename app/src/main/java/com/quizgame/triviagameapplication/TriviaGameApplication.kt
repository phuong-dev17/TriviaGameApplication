package com.quizgame.triviagameapplication

import android.app.Application
import com.quizgame.triviagameapplication.AccessStatusPreferencesDataStore.Companion.accessStatusDataStore

class TriviaGameApplication : Application() {
    private lateinit var _accessStatusRepository: AccessStatusRepository
    val accessStatusRepository
        get() = _accessStatusRepository

    override fun onCreate() {
        super.onCreate()
        _accessStatusRepository = AccessStatusRepository(
            dataStore = applicationContext.accessStatusDataStore
        )
    }
}
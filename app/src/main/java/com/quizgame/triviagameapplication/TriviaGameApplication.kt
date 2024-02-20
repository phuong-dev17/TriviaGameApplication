package com.quizgame.triviagameapplication

import android.app.Application
import com.quizgame.triviagameapplication.datastore.AccessStatusPreferencesDataStore.Companion.accessStatusDataStore
import com.quizgame.triviagameapplication.datastore.AccessStatusRepository

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
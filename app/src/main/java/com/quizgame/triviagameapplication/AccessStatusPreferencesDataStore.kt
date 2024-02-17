package com.quizgame.triviagameapplication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


class AccessStatusPreferencesDataStore {
    companion object {
        private const val ACCESS_STATUS_DATASTORE_NAME = "access_status_datastore"
        val Context.accessStatusDataStore: DataStore<Preferences> by preferencesDataStore(
            name = ACCESS_STATUS_DATASTORE_NAME
        )
    }
}

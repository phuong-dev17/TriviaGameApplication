package com.quizgame.triviagameapplication

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class AccessStatusRepository (private val dataStore: DataStore<Preferences>) {
    private companion object {
        val HAS_ACCESSED = booleanPreferencesKey(name ="hasAccessed")
        const val LOG_TAG ="P123"
    }

    val hasAccessed: Flow<AccessStatus> = dataStore.data
        .catch { error ->
            if (error is IOException) {
                Log.e(LOG_TAG, "Failed to read datastore")
                emit(emptyPreferences())
            } else {
                throw error
            }
        }
        .map { preferences ->
            val isFirstTimeAccess = preferences[HAS_ACCESSED]?: true
                AccessStatus(isFirstTimeAccess)
        }

    suspend fun put(accessStatus : AccessStatus) :Boolean {
        val result = runCatching {
            dataStore.edit { preferences ->
                preferences[HAS_ACCESSED] = accessStatus.isFirstTimeAccess
            }
        }
        return result.isSuccess
    }

}
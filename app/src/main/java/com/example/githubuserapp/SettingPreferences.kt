package com.example.githubuserapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>){

    private val THEME_KEY = intPreferencesKey("theme_settings")

    fun getThemeSetting(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: 0
        }
    }
    suspend fun saveThemeSetting(checkedItem: Int) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = checkedItem
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
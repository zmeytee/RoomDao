package ru.vladimir.tilikov.roomdaomessenger.data.repositories

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPrefsRepository(context: Context, sharedPrefsName: String) {

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        sharedPrefsName,
        Context.MODE_PRIVATE
    )

    suspend fun getSharedPrefsStringOf(key: String): String? {
        return withContext(Dispatchers.IO) {
            sharedPrefs.getString(key, null)
        }
    }

    suspend fun editSharedPrefs(key: String, value: Any) {
        withContext(Dispatchers.IO) {
            when (value) {
                is String -> sharedPrefs.edit().putString(key, value).apply()
                is Boolean -> sharedPrefs.edit().putBoolean(key, value).apply()
                is Long -> sharedPrefs.edit().putLong(key, value).apply()
                else -> error("Unknown value = $value (SharedPrefsRepository: editSharedPrefs)")
            }
        }
    }
}
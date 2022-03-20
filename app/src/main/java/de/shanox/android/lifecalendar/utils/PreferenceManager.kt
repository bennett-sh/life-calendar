package de.shanox.android.lifecalendar.utils

import android.content.Context
import android.content.SharedPreferences

import java.time.format.DateTimeParseException
import java.time.format.DateTimeFormatter
import java.time.LocalDate

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "preferences",
        0
    )

    fun storePreference(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun storePreference(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun storePreference(key: String, value: Float) {
        prefs.edit().putFloat(key, value).apply()
    }

    fun storePreference(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun storePreference(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    fun storePreference(key: String, value: Set<String>) {
        prefs.edit().putStringSet(key, value).apply()
    }

    fun storeLocalDate(key: String, value: LocalDate) {
        prefs.edit().putString(key, value.toString()).apply()
    }

    fun getLocalDate(key: Any): LocalDate? {
        val ds = prefs.all[key].toString()

        try {
            return LocalDate.parse(ds, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch(ex: DateTimeParseException) {
            return null
        }
    }

    fun getPreference(key: Any): Any? {
        return prefs.all[key]
    }

}

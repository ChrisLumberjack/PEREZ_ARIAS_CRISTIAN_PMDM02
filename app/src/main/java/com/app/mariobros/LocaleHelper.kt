package com.app.mariobros

import android.content.Context
import android.content.res.Configuration
import android.content.SharedPreferences
import java.util.*

object LocaleHelper {

    private const val PREFS_NAME = "app_prefs"
    private const val KEY_LANGUAGE = "language"

    // Método para guardar la preferencia de idioma
    fun setLocale(context: Context, language: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(KEY_LANGUAGE, language)
        editor.apply()

        // Cambiar el idioma de la aplicación
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    // Método para recuperar la preferencia de idioma
    fun getSavedLanguagePreference(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, "en") ?: "en"  // Default to English if no preference is saved
    }
}

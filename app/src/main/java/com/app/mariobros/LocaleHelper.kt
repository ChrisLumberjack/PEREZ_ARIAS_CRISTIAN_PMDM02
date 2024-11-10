package com.app.mariobros

import android.content.Context
import android.content.res.Configuration
import android.content.SharedPreferences
import java.util.*

object LocaleHelper {

    // Nombre del archivo de preferencias compartidas donde se guardará el idioma
    private const val PREFS_NAME = "app_prefs"
    // Clave para almacenar el idioma en las preferencias
    private const val KEY_LANGUAGE = "language"

    /**
     * Método para guardar la preferencia de idioma en SharedPreferences
     * @param context El contexto de la aplicación, necesario para acceder a SharedPreferences
     * @param language El código del idioma que se desea guardar, por ejemplo, "en" para inglés o "es" para español
     */
    fun setLocale(context: Context, language: String) {
        // Obtener las preferencias compartidas
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // Crear un editor para modificar las preferencias
        val editor = prefs.edit()
        // Guardar el idioma en las preferencias
        editor.putString(KEY_LANGUAGE, language)
        // Aplicar los cambios
        editor.apply()

        // Cambiar el idioma de la aplicación
        val locale = Locale(language) // Crear un objeto Locale con el código de idioma
        Locale.setDefault(locale) // Establecer el nuevo idioma como el idioma por defecto
        val config = Configuration() // Crear una nueva configuración
        config.locale = locale // Asignar el locale a la configuración
        // Actualizar la configuración de los recursos de la aplicación para reflejar el cambio de idioma
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    /**
     * Método para recuperar la preferencia de idioma guardada en SharedPreferences
     * @param context El contexto de la aplicación
     * @return El código del idioma guardado, por defecto "en" si no hay idioma guardado
     */
    fun getSavedLanguagePreference(context: Context): String {
        // Obtener las preferencias compartidas
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // Recuperar el idioma guardado, si no hay se retorna "en" por defecto
        return prefs.getString(KEY_LANGUAGE, "en") ?: "en"  // Por defecto se queda guardado en English
    }
}

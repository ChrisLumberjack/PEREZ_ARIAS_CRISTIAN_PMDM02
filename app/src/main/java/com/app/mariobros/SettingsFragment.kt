package com.app.mariobros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.app.mariobros.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    // Declaramos el binding para acceder a las vistas de este fragmento
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla la vista del fragmento y configura el enlace de datos
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        // Obtener el idioma guardado en las preferencias del usuario (por defecto, 'en' para inglés)
        val language = LocaleHelper.getSavedLanguagePreference(requireContext())

        // Configurar el estado inicial del switch según el idioma guardado
        // Si el idioma guardado es español ('es'), el switch estará activado
        binding.switchLanguage.isChecked = language == "es"

        // Actualizar el texto del Switch según el idioma guardado
        updateSwitchText(language)


        // Configuración del listener (escuchador) para el switch
        // Esto nos permitirá cambiar el idioma cuando el usuario cambie la posición del switch
        binding.switchLanguage.setOnCheckedChangeListener { _, isChecked ->
            // Determinar el nuevo idioma según el estado del switch
            val newLanguage = if (isChecked) "es" else "en"

            // Cambiar el idioma de la aplicación usando el helper (método estático de LocaleHelper)
            LocaleHelper.setLocale(requireContext(), newLanguage)

            // Actualizar el texto del Switch según el nuevo idioma
            updateSwitchText(newLanguage)

            // Reiniciar la actividad para que los cambios de idioma tengan efecto inmediatamente
            requireActivity().recreate()

            // Mostrar un Toast para notificar al usuario sobre el cambio de idioma
            Toast.makeText(
                requireContext(),
                if (isChecked) "Cambio a Español" else "Change to English",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Devolver la vista inflada para que se pueda mostrar
        return binding.root
    }

    // Esta función actualiza el texto del Switch según el idioma seleccionado
    private fun updateSwitchText(language: String) {
        // Si el idioma es español ('es'), cambiamos el texto del switch a "Cambiar a Inglés"
        // Si el idioma es inglés ('en'), cambiamos el texto a "Cambiar a Español"
        if (language == "es") {
            binding.switchLanguage.text = "Cambiar a Inglés"
        } else {
            binding.switchLanguage.text = "Cambiar a Español"
        }
    }

    // Método que se llama cuando la vista del fragmento se destruye
    // Usamos este método para liberar los recursos relacionados con el binding
    override fun onDestroyView() {
        super.onDestroyView()
        // Liberar el binding para evitar fugas de memoria
        _binding = null
    }

    // Método para crear una nueva instancia del fragmento
    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}

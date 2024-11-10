package com.app.mariobros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.mariobros.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla la vista y configura el enlace de datos
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        // Configuración del estado inicial del switch según la preferencia guardada
        val language = LocaleHelper.getSavedLanguagePreference(requireContext())
        binding.switchLanguage.isChecked = language == "es"

        // Actualizar el texto del Switch según el idioma
        updateSwitchText(language)

        // Configuración del listener del switch para cambiar el idioma
        binding.switchLanguage.setOnCheckedChangeListener { _, isChecked ->
            val newLanguage = if (isChecked) "es" else "en"
            LocaleHelper.setLocale(requireContext(), newLanguage)

            // Actualizar el texto del Switch según el idioma seleccionado
            updateSwitchText(newLanguage)

            // Reiniciar la actividad para aplicar el cambio de idioma
            requireActivity().recreate()

            Toast.makeText(
                requireContext(),
                if (isChecked) "Cambio a Español" else "Change to English",
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }

    private fun updateSwitchText(language: String) {
        if (language == "es") {
            binding.switchLanguage.text = "Cambiar a Inglés"
        } else {
            binding.switchLanguage.text = "Cambiar a Español"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}

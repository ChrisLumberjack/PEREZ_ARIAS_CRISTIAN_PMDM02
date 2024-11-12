package com.app.mariobros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.mariobros.databinding.FragmentHomeBinding
import kotlin.system.exitProcess

// TODO: Renombrar los parámetros para que coincidan con los nombres que se usan al inicializar el fragmento.
// Ejemplo: usar ARG_ITEM_NUMBER si ese es el caso.
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento de inicio simple, donde el usuario puede navegar a otros fragmentos o cerrar la aplicación.
 * Usa el método de fábrica [HomeFragment.newInstance] para crear una nueva instancia de este fragmento.
 */
class HomeFragment : Fragment() {
    // Variable para el enlace a la vista (Binding). Usada para acceder de manera segura a las vistas del fragmento.
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Parámetros que se pasan al fragmento desde el fragmento anterior o la actividad
    private var param1: String? = null
    private var param2: String? = null

    /**
     * Método que se ejecuta al crear el fragmento. Aquí se recuperan los argumentos pasados a través de un `Bundle`.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recuperar los argumentos enviados al fragmento.
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    /**
     * Este método infla el layout del fragmento y realiza la configuración de los botones.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento y asignar el binding.
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Configuración del primer botón. Al hacer clic, se navega al fragmento ListCharactersFragment.
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listCharactersFragment)
        }

        // Configuración del segundo botón. Al hacer clic, se cierra la aplicación.
        binding.button2.setOnClickListener {
            // exitProcess(1) termina la aplicación de manera abrupta. 1 es el código de salida.
            exitProcess(1)
        }

        // Configurar el Toolbar (sin código aquí, probablemente se configura en otro lugar).

        return binding.root // Retornar la vista raíz del fragmento para que se muestre en la interfaz.
    }

    companion object {
        /**
         * Método de fábrica para crear una nueva instancia del fragmento con los parámetros proporcionados.
         * @param param1 Primer parámetro a pasar al fragmento.
         * @param param2 Segundo parámetro a pasar al fragmento.
         * @return Una nueva instancia del fragmento HomeFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    // Guardar los parámetros en el `Bundle` para que el fragmento pueda acceder a ellos más tarde.
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

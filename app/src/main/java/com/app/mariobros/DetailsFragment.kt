package com.app.mariobros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.mariobros.databinding.FragmentDetailsBinding
import com.app.mariobros.databinding.FragmentHomeBinding

// TODO: Renombrar los parámetros para que coincidan con los nombres que se usan al inicializar el fragmento.
// Ejemplo: usar ARG_ITEM_NUMBER si ese es el caso.
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Un fragmento simple de detalles que muestra información más detallada.
 * Usa el método de fábrica [DetailsFragment.newInstance] para crear una nueva instancia de este fragmento.
 */
class DetailsFragment : Fragment() {
    // Variable para el enlace a la vista (Binding). Se usa para acceder a las vistas del fragmento.
    private var _binding: FragmentDetailsBinding? = null
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
     * Este método infla el layout del fragmento y realiza la lógica de inicialización de las vistas.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento y asignar el binding.
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        // Recuperar los datos enviados en el 'Bundle' desde el fragmento anterior o la actividad.
        val imageId = arguments?.getInt("IMAGE_ID") ?: -1  // Si no se pasa un ID de imagen, asignar -1 como valor predeterminado.
        val heading = arguments?.getString("HEADING")      // Título o encabezado del personaje.
        val skill = arguments?.getString("SKILL")          // Habilidad o habilidad asociada al personaje.

        // Verificar que los datos son válidos antes de mostrar la información en la vista.
        if (imageId != -1 && heading != null) {
            // Si los datos son válidos, configurar la imagen y los textos correspondientes en la vista.
            binding.detailImage.setImageResource(imageId)
            binding.detailHeading.text = heading
            binding.detailSkills.text = skill

            // Mostrar un mensaje de Toast confirmando el personaje seleccionado.
            Toast.makeText(requireContext(), getString(R.string.selected_character, heading), Toast.LENGTH_SHORT).show()
        } else {
            // Si no se pasaron datos válidos, cerrar el fragmento actual y volver al fragmento anterior.
            parentFragmentManager.popBackStack()
        }

        // Retornar la vista raíz del fragmento para que se muestre en la interfaz.
        return binding.root
    }

    companion object {
        /**
         * Método de fábrica para crear una nueva instancia del fragmento con los parámetros proporcionados.
         * @param param1 Primer parámetro a pasar al fragmento.
         * @param param2 Segundo parámetro a pasar al fragmento.
         * @return Una nueva instancia del fragmento DetailsFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    // Guardar los parámetros en el `Bundle` para que el fragmento pueda acceder a ellos más tarde.
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

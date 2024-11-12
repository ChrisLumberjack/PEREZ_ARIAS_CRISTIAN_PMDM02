package com.app.mariobros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController // Importante para usar NavController en lugar de FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mariobros.databinding.FragmentListcharactersBinding
import com.google.android.material.snackbar.Snackbar
import com.app.mariobros.list.List
import com.app.mariobros.list.ListAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento de lista de personajes. Muestra una lista de personajes en un RecyclerView
 * y muestra un mensaje de bienvenida cuando se carga.
 * Usa el método de fábrica [ListCharactersFragment.newInstance] para crear una nueva instancia del fragmento.
 */
class ListCharactersFragment : Fragment() {
    // Variable para el enlace a la vista (Binding), lo que permite acceder a las vistas del fragmento de manera segura
    private var _binding: FragmentListcharactersBinding? = null
    private val binding get() = _binding!!

    // Variables necesarias para configurar el RecyclerView y los datos
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<List> // Lista para almacenar los datos de los personajes

    // Arrays que contienen los datos (imágenes, encabezados y habilidades) para cada personaje
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var skill: Array<String>

    // Parámetros opcionales para el fragmento (no se utilizan en este caso)
    private var param1: String? = null
    private var param2: String? = null

    /**
     * Método que se ejecuta al crear el fragmento. Aquí se recuperan los argumentos pasados al fragmento.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recuperar los parámetros enviados al fragmento a través del Bundle.
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    /**
     * Infla el layout del fragmento, configura los datos y el RecyclerView.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento usando el binding
        _binding = FragmentListcharactersBinding.inflate(inflater, container, false)

        // Mostrar un Snackbar con un mensaje de bienvenida al cargar el fragmento.
        Snackbar.make(binding.root, getString(R.string.welcome_message), Snackbar.LENGTH_LONG).show()

        // Inicializar los datos para el RecyclerView (listas de imágenes, títulos y habilidades)
        imageId = arrayOf(
            R.drawable.mario,
            R.drawable.luigi,
            R.drawable.peach,
            R.drawable.toad,
            R.drawable.yoshi,
            R.drawable.dk,
            R.drawable.wario,
            R.drawable.browser
        )
        heading = arrayOf(
            getString(R.string.mario_heading),
            getString(R.string.luigi_heading),
            getString(R.string.peach_heading),
            getString(R.string.toad_heading),
            getString(R.string.yoshi_heading),
            getString(R.string.dk_heading),
            getString(R.string.wario_heading),
            getString(R.string.bowser_heading)
        )

        skill = arrayOf(
            getString(R.string.mario_skill),
            getString(R.string.luigi_skill),
            getString(R.string.peach_skill),
            getString(R.string.toad_skill),
            getString(R.string.yoshi_skill),
            getString(R.string.dk_skill),
            getString(R.string.wario_skill),
            getString(R.string.bowser_skill)
        )

        // Configuración del RecyclerView para mostrar los personajes.
        newRecyclerView = binding.recicleviewPj // Obtener referencia del RecyclerView desde el binding
        newRecyclerView.layoutManager = LinearLayoutManager(activity) // Usar un LinearLayoutManager para un desplazamiento vertical.
        newRecyclerView.setHasFixedSize(true) // Mejorar el rendimiento si el tamaño de los elementos no cambia.
        newArrayList = arrayListOf<List>() // Inicializar la lista que contendrá los datos de los personajes.

        // Llamar al método que llena la lista con los datos de personajes.
        getUserData()

        return binding.root // Retornar la vista raíz del fragmento para que se muestre en la interfaz.
    }

    /**
     * Método para llenar la lista con los datos de los personajes y configurar el adaptador.
     * Este método llena el ArrayList con objetos de tipo 'List', que contienen los datos de cada personaje.
     */
    private fun getUserData() {
        for (i in imageId.indices) {
            // Crear un objeto 'List' para cada personaje con su imagen, título y habilidad
            val news = List(imageId[i], heading[i], skill[i])
            newArrayList.add(news) // Agregar el objeto 'List' a la lista
        }

        // Configurar el adaptador del RecyclerView con la lista de personajes y el NavController
        newRecyclerView.adapter = ListAdapter(newArrayList, findNavController()) // Usar 'findNavController' en lugar de 'parentFragmentManager'
    }

    /**
     * Limpiar la referencia de binding cuando la vista del fragmento se destruye.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpiar la referencia de binding para evitar fugas de memoria
    }

    companion object {
        /**
         * Método de fábrica para crear una nueva instancia del fragmento con los parámetros proporcionados.
         * Este método es útil para pasar datos a través del Bundle cuando se crea una nueva instancia del fragmento.
         * @param param1 Primer parámetro que se pasará al fragmento.
         * @param param2 Segundo parámetro que se pasará al fragmento.
         * @return Una nueva instancia del fragmento ListCharactersFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListCharactersFragment().apply {
                arguments = Bundle().apply {
                    // Guardar los parámetros en el Bundle para que el fragmento pueda acceder a ellos más tarde.
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

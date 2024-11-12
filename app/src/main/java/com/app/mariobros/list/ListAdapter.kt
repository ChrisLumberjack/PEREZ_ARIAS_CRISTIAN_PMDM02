package com.app.mariobros.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.app.mariobros.DetailsFragment
import com.app.mariobros.R
import com.app.mariobros.databinding.ItemCharacterBinding

class ListAdapter(private val newsListado: ArrayList<List>, private val navController: NavController) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    // Este método infla el layout de cada item en el RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Este método obtiene el número de items en la lista
    override fun getItemCount(): Int {
        return newsListado.size
    }

    // Este método vincula los datos de cada item con su vista en el RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsListado[position]
        holder.bind(currentItem, navController) // Cambié fragmentManager por navController
    }

    // ViewHolder es donde definimos cómo se vinculan los datos con los elementos UI de cada item
    class ViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        // Este método recibe los datos del listado y la navegación del navController
        fun bind(listado: List, navController: NavController) {
            // Asignamos las imágenes y textos a los elementos de la vista
            binding.titleImage.setImageResource(listado.titleImage)
            binding.tvHeading.text = listado.Heading

            // Cuando se hace clic en el item, navegamos hacia el fragment de detalles
            binding.root.setOnClickListener {
                // Pasamos los datos necesarios en un Bundle para la navegación
                val imageId = listado.titleImage
                val heading = listado.Heading
                val skill = listado.Skills

                val bundle = Bundle().apply {
                    putInt("IMAGE_ID", imageId)
                    putString("HEADING", heading)
                    putString("SKILL", skill)
                }

                // Usamos el NavController para navegar al fragment de detalles
                navController.navigate(R.id.action_listCharactersFragment_to_detailsFragment, bundle)
            }
        }
    }
}

package com.app.mariobros.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mariobros.DetailsFragment
import com.app.mariobros.R
import com.app.mariobros.databinding.ItemCharacterBinding

class ListAdapter(private val newsListado: ArrayList<List>, private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsListado.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsListado[position]
        holder.bind(currentItem, fragmentManager)
    }

    class ViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listado: List, fragmentManager: FragmentManager) {
            binding.titleImage.setImageResource(listado.titleImage)
            binding.tvHeading.text = listado.Heading
            binding.root.setOnClickListener {
                val imageId = listado.titleImage
                val heading = listado.Heading
                val skill = listado.Skills

                val detailsFragment = DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putInt("IMAGE_ID", imageId)
                        putString("HEADING", heading)
                        putString("SKILL", skill)
                    }
                }

                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailsFragment) // Asegúrate de que 'fragment_container' es el ID de tu contenedor de fragmentos
                    .addToBackStack(null) // Opcional: agrega esta transacción a la pila de retroceso
                    .commit()
            }
        }
    }
}

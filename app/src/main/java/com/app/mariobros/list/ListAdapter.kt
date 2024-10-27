package com.app.mariobros.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mariobros.databinding.ItemCharacterBinding

class ListAdapter(private val newsListado: ArrayList<List>, private val context: Context) :
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
        holder.bind(currentItem, context)
    }

    class ViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listado: List, context: Context) {
            binding.titleImage.setImageResource(listado.titleImage)
            binding.tvHeading.text = listado.Heading
        }
    }
}

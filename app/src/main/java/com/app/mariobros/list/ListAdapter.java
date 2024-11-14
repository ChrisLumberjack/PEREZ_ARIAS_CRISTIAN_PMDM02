package com.app.mariobros.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import com.app.mariobros.R;
import com.app.mariobros.databinding.ItemCharacterBinding;
import java.util.ArrayList;

/**
 * ListAdapter es una clase de adaptador personalizada para RecyclerView que maneja una lista de objetos List.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<List> newsListado;
    private NavController navController;

    /**
     * Constructor del adaptador.
     * @param newsListado La lista de objetos List que se mostrarán en el RecyclerView.
     * @param navController El controlador de navegación para manejar la navegación entre fragmentos.
     */
    public ListAdapter(ArrayList<List> newsListado, NavController navController) {
        this.newsListado = newsListado;
        this.navController = navController;
    }

    /**
     * Crea nuevos ViewHolder cuando el RecyclerView lo solicita.
     * @param parent El ViewGroup en el que se agregará la nueva vista después de vincularla a una posición de adaptador.
     * @param viewType El tipo de vista de la nueva vista.
     * @return Un nuevo ViewHolder que contiene una vista para representar el ítem.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    /**
     * Vincula los datos del ítem en la posición especificada al ViewHolder proporcionado.
     * @param holder El ViewHolder que debe actualizarse para representar el contenido del ítem en la posición dada en el conjunto de datos.
     * @param position La posición del ítem dentro del conjunto de datos del adaptador.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List currentItem = newsListado.get(position);
        holder.bind(currentItem, navController);
    }

    /**
     * Devuelve el número total de ítems en el conjunto de datos que mantiene el adaptador.
     * @return El tamaño del conjunto de datos.
     */
    @Override
    public int getItemCount() {
        return newsListado.size();
    }

    /**
     * ViewHolder describe una vista de ítem y metadatos sobre su lugar dentro del RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;

        /**
         * Constructor del ViewHolder.
         * @param binding El binding para acceder a las vistas de cada ítem.
         */
        public ViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Vincula los datos del objeto List a las vistas de este ViewHolder.
         * @param listado El objeto List que contiene los datos del ítem.
         * @param navController El controlador de navegación para manejar la navegación al hacer clic en un ítem.
         */
        public void bind(List listado, NavController navController) {
            binding.titleImage.setImageResource(listado.getTitleImage());
            binding.tvHeading.setText(listado.getHeading());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int imageId = listado.getTitleImage();
                    String heading = listado.getHeading();
                    String skill = listado.getSkills();

                    Bundle bundle = new Bundle();
                    bundle.putInt("IMAGE_ID", imageId);
                    bundle.putString("HEADING", heading);
                    bundle.putString("SKILL", skill);

                    navController.navigate(R.id.action_listCharactersFragment_to_detailsFragment, bundle);
                }
            });
        }
    }
}

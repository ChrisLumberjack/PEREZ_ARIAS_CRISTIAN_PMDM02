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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<List> newsListado;
    private NavController navController;

    public ListAdapter(ArrayList<List> newsListado, NavController navController) {
        this.newsListado = newsListado;
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List currentItem = newsListado.get(position);
        holder.bind(currentItem, navController);
    }

    @Override
    public int getItemCount() {
        return newsListado.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;

        public ViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

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

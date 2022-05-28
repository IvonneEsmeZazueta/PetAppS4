package com.example.petapp.domain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petapp.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class AdapterPets extends RecyclerView.Adapter<AdapterPets.ViewHolderPets> {

    private final List<Pets> pets;
    private Intent intent;

    public AdapterPets(List<Pets> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public ViewHolderPets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet_first, null, false);
        return new ViewHolderPets(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPets holder, @SuppressLint("RecyclerView") int position) {
        holder.namePet.setText(pets.get(position).getName());
        Picasso.get()
                .load(pets.get(position).getImg())
                .placeholder(R.drawable.paw)
                .error(R.drawable.paw)
                .into(holder.ivPet);
        holder.countPet.setText(""+pets.get(position).getBones());
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class ViewHolderPets extends RecyclerView.ViewHolder {

        ImageView ivPet;
        ImageView ivLike;
        TextView namePet, countPet;

        public ViewHolderPets(@NonNull View itemView) {
            super(itemView);
            ivPet = itemView.findViewById(R.id.ivPet);
            ivLike = itemView.findViewById(R.id.ivLike);
            namePet = itemView.findViewById(R.id.namePet);
            countPet = itemView.findViewById(R.id.countPet);
        }
    }
}

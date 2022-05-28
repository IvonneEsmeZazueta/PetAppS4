package com.example.petapp.ui.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petapp.R;
import com.example.petapp.core.Data;
import com.example.petapp.databinding.FragmentTabBinding;
import com.example.petapp.domain.AdapterPets;
import com.example.petapp.domain.AdapterPetsGrid;
import com.example.petapp.domain.Pet;
import com.google.gson.GsonBuilder;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentTabBinding binding;
    private static Context context;

    public static PlaceholderFragment newInstance(int index, Context context2) {
        context = context2;
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentTabBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.recyclerView;
        final CircularImageView ivPet = binding.ivPet;
        final TextView tvName = binding.tvName;
        final View space = binding.space;
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if(s.equals("1")){
                    String dataPets = Data.DATA_PET;

                    Pet data = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(dataPets, Pet.class);

                    if(data.getPets().size() > 0){
                        AdapterPets adapter = new AdapterPets(data.getPets());

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    }
                } else {
                    String dataPets = Data.DATA_PET_FAVORITE_GRID;

                    Pet data = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(dataPets, Pet.class);

                    Picasso.get()
                            .load(data.getPets().get(0).getImg())
                            .placeholder(R.drawable.paw)
                            .error(R.drawable.paw)
                            .into(ivPet);

                    tvName.setText(data.getPets().get(0).getName());

                    if(data.getPets().size() > 0){
                        AdapterPetsGrid adapter = new AdapterPetsGrid(data.getPets());

                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false));
                    }

                    ivPet.setVisibility(View.VISIBLE);
                    tvName.setVisibility(View.VISIBLE);
                    space.setVisibility(View.VISIBLE);
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
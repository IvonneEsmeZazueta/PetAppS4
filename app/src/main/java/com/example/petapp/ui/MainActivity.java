package com.example.petapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import com.example.petapp.R;
import com.example.petapp.core.Data;
import com.example.petapp.domain.AdapterPets;
import com.example.petapp.domain.Pet;
import com.google.android.material.button.MaterialButton;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private RecyclerView recyclerView;
    private ImageView star;
    private ImageView menuIcon;
    private ImageView pawIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        star = findViewById(R.id.star);
        menuIcon = findViewById(R.id.menuIcon);
        pawIcon = findViewById(R.id.pawIcon);

        String dataPets = Data.DATA_PET;

        Pet data = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(dataPets, Pet.class);

        if(data.getPets().size() > 0){
            AdapterPets adapter = new AdapterPets(data.getPets());

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        pawIcon.setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });

        star.setOnClickListener(v -> {
            startActivity(new Intent(this, TabActivity.class));
        });

        menuIcon.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.setOnMenuItemClickListener(this);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.menu, popupMenu.getMenu());
            popupMenu.show();
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menu){
        switch (menu.getItemId()){
            case R.id.contact:
                startActivity(new Intent(getApplicationContext(), MailActivity.class));
                return true;
            case R.id.aboutMe:
                startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                return true;
            default:
                return false;
        }
    }
}
package com.example.petapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.petapp.R;
import com.example.petapp.core.Data;
import com.example.petapp.domain.AdapterPets;
import com.example.petapp.domain.Pet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SecondActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private RecyclerView recyclerView;
    private ImageView back;
    private ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);
        menuIcon = findViewById(R.id.menuIcon);

        String dataPets = Data.DATA_PET_FAVORITE;

        Pet data = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(dataPets, Pet.class);

        if(data.getPets().size() > 0){
            AdapterPets adapter = new AdapterPets(data.getPets());

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        back.setOnClickListener(v -> onBackPressed());

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
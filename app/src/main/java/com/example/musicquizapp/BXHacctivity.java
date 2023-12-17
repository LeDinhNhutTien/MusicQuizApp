package com.example.musicquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicquizapp.model.ListQuestion;
import com.example.musicquizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BXHacctivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private com.example.musicquizapp.rankingAdapter rankingAdapter;
    private List<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bxh);

        recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        playerList = (List<Player>) intent.getSerializableExtra("playerList");
        rankingAdapter = new rankingAdapter(playerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rankingAdapter);
    }
}

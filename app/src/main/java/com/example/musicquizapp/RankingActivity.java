package com.example.musicquizapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private com.example.musicquizapp.rankingAdapter rankingAdapter;
    private List<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bxh);

        recyclerView = findViewById(R.id.recyclerView);
        playerList = generateSampleData();
//  thay bằng dữ liệu thực tế
        rankingAdapter = new rankingAdapter(playerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rankingAdapter);
    }

    // dữ liệu mẫu
    private List<Player> generateSampleData() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player1", 1500));
        players.add(new Player("Player2", 1200));
        players.add(new Player("Player3", 1000));
        players.add(new Player("Player4", 900));
        players.add(new Player("Player7", 800));
        players.add(new Player("Player6", 700));
        players.add(new Player("Player5", 600));
        // Thêm nhiều người chơi khác nếu cần
        return players;
    }
}

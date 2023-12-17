package com.example.musicquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class RankingActivity extends AppCompatActivity {

    TextView score;
    Player player;
    Button again,BXH;
    private List<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done_game);
        Intent intent1 = getIntent();
        player = (Player) intent1.getSerializableExtra("player");
        Account account = (Account) intent1.getSerializableExtra("account");
        score = findViewById(R.id.textViewSc);
        score.setText("Điểm của bạn là "+ player.getScore());
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://quanlycauhoinhac-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Player").child(account.getId());
        myRef.child("Score").setValue(player.getScore());

        BXH=findViewById(R.id.buttonBXH);
        BXH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://quanlycauhoinhac-default-rtdb.asia-southeast1.firebasedatabase.app");
                DatabaseReference myRef = database.getReference("Player");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            Player data = childSnapshot.getValue(Player.class);
                            if (playerList == null) {
                                playerList = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null

                            }
                            playerList.add(data);
                            Intent intent = new Intent(RankingActivity.this, BXHacctivity.class);
                            intent.putExtra("playerList", (Serializable) playerList);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


        });

    }
}

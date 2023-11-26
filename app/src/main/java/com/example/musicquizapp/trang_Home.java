package com.example.musicquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class trang_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_home);
        ImageView buttonCrea= findViewById(R.id.imageView9);
        ImageView buttonCrea2= findViewById(R.id.JoinRoom1);
        buttonCrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trang_Home.this, create_room.class);
                startActivity(intent);
            }
        });


        buttonCrea2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trang_Home.this, JoinRoomActivity.class);
                startActivity(intent);
            }
        });


    }
}
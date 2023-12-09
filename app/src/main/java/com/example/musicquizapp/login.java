package com.example.musicquizapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.musicquizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    DatabaseReference database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(login.this, My_question.class);
//                startActivity(intent);
//
                Intent intent = new Intent(login.this, trang_Home.class);
                startActivity(intent);
            }
        });
        TextView Register = findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, ma_otp.class);
                startActivity(intent);
                getData();
            }
        });

    }
    private void getData(){
        database = FirebaseDatabase.getInstance("https://quanlycauhoinhac-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Question");
        database.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Question pr = snapshot.getValue(Question.class);
                System.out.println(pr.getAnswer1());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
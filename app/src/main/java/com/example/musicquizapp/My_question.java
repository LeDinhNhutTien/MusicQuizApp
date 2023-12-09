package com.example.musicquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.musicquizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class My_question extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    DatabaseReference database ;

    ArrayList<Question> list = new ArrayList<Question>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_question);
        Button buttonLogin = findViewById(R.id.buttonNextQ);
        TextView textView17 = findViewById(R.id.textView17);

        getData();

    }
    private void getData(){


        database= FirebaseDatabase.getInstance().getReference("QuestionInfor");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.v(TAG, "onDataChange() called");
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    // Lấy dữ liệu từ childSnapshot và xử lý
                    Question data = childSnapshot.getValue(Question.class);
                    // ...
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.v(TAG, "onCancelled() called");
                Log.e(TAG, "Error: " + error.getMessage());
            }
        });
    }
}
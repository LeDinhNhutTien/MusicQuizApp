package com.example.musicquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.musicquizapp.model.ListQuestion;

public class My_question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        Button buttonLogin = findViewById(R.id.buttonNextQ);
        ListQuestion lstQuestion = (ListQuestion) getIntent().getSerializableExtra("lstQuestionObj");
        System.out.println(lstQuestion);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_question.this, Question_dien_khuyet.class);
                startActivity(intent);
            }
        });
    }
}
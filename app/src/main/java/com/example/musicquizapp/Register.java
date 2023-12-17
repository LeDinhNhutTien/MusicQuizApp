package com.example.musicquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.utilities.Score;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
     EditText Username, Password,SDT,CfPassword;

    TextView buttonLogin ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonLogin = findViewById(R.id.BTloginonRGT);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, login.class);
                startActivity(intent);
            }
        });
        Username=findViewById(R.id.phoneNumberEditText2);
        SDT= findViewById(R.id.phoneNumberEditText);
        Password=findViewById(R.id.passwordEditText);
        CfPassword=findViewById(R.id.passwordEditText2);
        Button btnSignUp=findViewById(R.id.buttonSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }


    private void register(){
        String Usernamee = Username.getText().toString().trim();
        String Passwordd = Password.getText().toString().trim();
        String sdt = SDT.getText().toString().trim();
        Player player = new Player( Usernamee, 0);
        Account account = new Account(sdt,"P008", Passwordd);
        // Lưu thông tin người dùng mới và o Firebase
        DatabaseReference databaseP,databaseA;
        databaseP = FirebaseDatabase.getInstance("https://quanlycauhoinhac-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Player");
        databaseP.child("P008").setValue(player);
        databaseA = FirebaseDatabase.getInstance("https://quanlycauhoinhac-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Account");
        databaseA.child(sdt).setValue(account, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error == null) {
                    Toast.makeText(Register.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(Register.this, "Sign up failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
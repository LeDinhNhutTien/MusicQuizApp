package com.example.musicquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicquizapp.model.ListQuestion;
import com.example.musicquizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText etUsername, etPassword;
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        etUsername = findViewById(R.id.editTextTextPersonName);
        etPassword = findViewById(R.id.editTextTextPersonName2);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SDT = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (SDT.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference database = FirebaseDatabase.getInstance("https://quanlycauhoinhac-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Account");
                database.child(SDT).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Account account = snapshot.getValue(Account.class);

                            if (account != null && account.getPass() != null && account.getPassword().equals(password)) {
                                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                loadQuestions(account);

                            } else {
                                Toast.makeText(MainActivity.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Tên đăng nhập không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        TextView Register = findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void loadQuestions(Account account) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://quanlycauhoinhac-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("question");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ListQuestion lstQuestion = new ListQuestion();
                ArrayList<Question> arrLstQuestion = new ArrayList<>();

                if (dataSnapshot.exists()) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        Question data = childSnapshot.getValue(Question.class);
                        arrLstQuestion.add(data);
                    }
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://quanlycauhoinhac-default-rtdb.asia-southeast1.firebasedatabase.app");
                    DatabaseReference myRef = database.getReference("Player").child(account.getId());

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Player player = dataSnapshot.getValue(Player.class);
                            Intent intent = new Intent(MainActivity.this, My_question.class);
                            lstQuestion.setMyObjectList(arrLstQuestion);
                            intent.putExtra("lstQuestionObj", lstQuestion);
                            intent.putExtra("player", (Serializable) player);
                            intent.putExtra("account", (Serializable) account);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(MainActivity.this, "Xảy ra lỗi khi tải câu hỏi từ Firebase", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, "Không có câu hỏi nào được tải lên từ Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Xảy ra lỗi khi tải câu hỏi từ Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

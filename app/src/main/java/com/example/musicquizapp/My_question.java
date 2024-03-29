package com.example.musicquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicquizapp.model.ListQuestion;
import com.example.musicquizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class My_question extends AppCompatActivity {

    private RadioGroup radioGroup;
    private int questionCount = 0;
    Question question;
    boolean isPlaying = true;
    private static int numberOfQuestion =4; // số lượng câu hỏi
    private MediaPlayer mediaPlayer;
    private ArrayList<Question> lstQuestiongenerated ;
     Player player;
    RadioButton choice1 ;

    ImageView playMusic;
    RadioButton choice2 ;
    RadioButton choice3 ;
    TextView textQuestion;
    TextView textQuestionPlay;
    RadioButton choice4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        Button buttonLogin = findViewById(R.id.buttonNextQ);
        ListQuestion lstQuestion = (ListQuestion) getIntent().getSerializableExtra("lstQuestionObj");
        lstQuestion.genQuestion(numberOfQuestion);
        lstQuestiongenerated = lstQuestion.getLstQuestion();
        initView();
        nextQuestion(questionCount);
        String url = question.getMediaUrl();
        new PlayMusicTask().execute(url);
        isPlaying = true;
        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if (!isPlaying) {
                    String url = question.getMediaUrl();
                    new PlayMusicTask().execute(url);
                    isPlaying = true;
                } else {
                    stopMusic();
                    isPlaying = false;
                }
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                player = (Player) intent1.getSerializableExtra("player");
                Account account = (Account) intent1.getSerializableExtra("account");
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                if(radioButton != null ) {
                    String textAnswer = (String) radioButton.getText();
                    if (textAnswer.equals(question.getCorrect_Choice())) {
                        player.addScore();
                        if (questionCount > numberOfQuestion) {
                            Toast.makeText(My_question.this, "Bạn hoàn thành ! điểm số của bạn là " + player.getScore() + " Điểm", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(My_question.this, "Đúng rồi, bạn được cộng 10 điểm", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        if (questionCount > numberOfQuestion) {
                            Toast.makeText(My_question.this, "Bạn hoàn thành ! điểm số của bạn là " + player.getScore() + " Điểm", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(My_question.this, "Sai rồi nè =((", Toast.LENGTH_SHORT).show();
                    }
                    stopMusic();
                    radioButton.setChecked(false);
                    if (questionCount > numberOfQuestion) {
                        Intent intent = new Intent(My_question.this, acctivity_bxh.class);
                        startActivity(intent);
                        // hết view hiển thị lên màn hình điểm số của người chơi
                    } else {
                        nextQuestion(questionCount);
                    }

                    Toast.makeText(My_question.this, "Sai rồi nè =((", Toast.LENGTH_SHORT).show();
                }
                stopMusic();
                radioButton.setChecked(false);
                if(questionCount > numberOfQuestion){
                    Intent intent = new Intent(My_question.this, RankingActivity.class);
                    intent.putExtra("player", (Serializable) player);
                    intent.putExtra("account", (Serializable) account);
                    startActivity(intent);
                    // hết view hiển thị lên màn hình điểm số của người chơi
                }
                else {
                    nextQuestion(questionCount);

                }
                else
                    Toast.makeText(My_question.this, "Bạn chưa trả lời mà :((", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(My_question.this, "Không được quay lại ở màn hình này", Toast.LENGTH_SHORT).show();
    }
    private void nextQuestion(int nowQuestion){
        int nowQ = questionCount + 1;
        int totalQ = numberOfQuestion + 1;
        textQuestion.setText( nowQ +"/" + totalQ );
        // gen random question
        question = lstQuestiongenerated.get(nowQuestion);
        String url = question.getMediaUrl();
        new PlayMusicTask().execute(url);
        isPlaying = true;
        textQuestionPlay.setText(question.getQuestion());

        ArrayList<String> lstQuestion = new ArrayList<>();
        lstQuestion.add(question.getAnswer1());
        lstQuestion.add(question.getAnswer2());
        lstQuestion.add(question.getAnswer3());
        lstQuestion.add(question.getCorrect_Choice());
        //shuffle this for random question
        Collections.shuffle(lstQuestion);
        //Set this question ?
        choice1.setText(lstQuestion.get(0));
        choice2.setText(lstQuestion.get(1));
        choice3.setText(lstQuestion.get(2));
        choice4.setText(lstQuestion.get(3));
        //prepare for next question
        questionCount ++;
    }
    private void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void initView()
    {
        radioGroup = findViewById(R.id.radioGroup);
        choice1 = findViewById(R.id.option1RadioButton);
        choice2 = findViewById(R.id.option1RadioButton2);
        choice3 = findViewById(R.id.option1RadioButton3);
        choice4 = findViewById(R.id.option1RadioButton4);
        textQuestion = findViewById(R.id.questionNumberTextView);
        textQuestionPlay = findViewById(R.id.textView17);
        playMusic = findViewById(R.id.imageView3);
        //media player
        mediaPlayer = new MediaPlayer();

    }

    //async class play music
    private class PlayMusicTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            String url = urls[0];
            try {
                mediaPlayer.reset();
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }


}
package com.example.musicquizapp.model;

import java.io.Serializable;
public class Question implements Serializable {
    String Answer1 ;
    String Answer2 ;
    String Answer3 ;
    String Correct_Choice;
    String Question;
    String Type;
    String MediaUrl;
    public Question() {

    }

    public Question(String answer1, String answer2, String answer3, String correct_Choice, String question, String type, String mediaUrl) {
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Correct_Choice = correct_Choice;
        Question = question;
        Type = type;
        MediaUrl = mediaUrl;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public String getCorrect_Choice() {
        return Correct_Choice;
    }

    public void setCorrect_Choice(String correct_Choice) {
        Correct_Choice = correct_Choice;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMediaUrl() {
        return MediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        MediaUrl = mediaUrl;
    }
}
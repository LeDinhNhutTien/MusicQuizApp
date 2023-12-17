package com.example.musicquizapp;

import java.io.Serializable;

public class Player implements Serializable {
    private String id;
    private String Name;
    private int Score;

    public Player(String Name, int Score) {
        this.Name = Name;
        this.Score = Score;
    }

    public Player() {

    }
    public Player( int score) {

        this.Score = score;
    }


    public String getName() {
        return Name;
    }

    public int getScore() {
        return Score;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setScore(int score) {
        this.Score = score;
    }

    public void addScore(){
        this.Score = this.Score + 10;
    }
}

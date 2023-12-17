package com.example.musicquizapp.model;

public class Player {
    String Name;
    int score ;
    String playAt;

    public Player() {
    }

    public Player(String name, int score) {
        this.Name = name;
        this.score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayAt() {
        return playAt;
    }

    public void setPlayAt(String playAt) {
        this.playAt = playAt;
    }
}

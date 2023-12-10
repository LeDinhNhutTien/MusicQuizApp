package com.example.musicquizapp;

public class Player {
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Player() {

    }
    public Player( int score) {

        this.score = score;
    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(){
        this.score = this.score + 10;
    }
}

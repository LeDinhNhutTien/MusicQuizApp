package com.example.musicquizapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListQuestion implements Serializable {

    ArrayList<Question> myObjectList = new ArrayList<>();

    public ListQuestion() {
    }

    public ListQuestion(ArrayList<Question> myObjectList) {
        this.myObjectList = myObjectList;
    }

    public ArrayList<Question> getMyObjectList() {
        return myObjectList;
    }

    public void setMyObjectList(ArrayList<Question> myObjectList) {
        this.myObjectList = myObjectList;
    }
}

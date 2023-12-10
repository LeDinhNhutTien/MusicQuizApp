package com.example.musicquizapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListQuestion implements Serializable {

    ArrayList<Question> myObjectList = new ArrayList<>();

    ArrayList<Question> lstQuestion = new ArrayList<>();

    public ListQuestion() {
    }

    public ListQuestion(ArrayList<Question> myObjectList) {
        this.myObjectList = myObjectList;
    }

    public ArrayList<Question> getLstQuestion() {
        return lstQuestion;
    }

    public ArrayList<Question> getMyObjectList() {
        return myObjectList;
    }

    public void setMyObjectList(ArrayList<Question> myObjectList) {
        this.myObjectList = myObjectList;
    }

    public void genQuestion(int numberOfElementsToSelect){
        numberOfElementsToSelect ++ ;
        Random random = new Random();
        ArrayList<Question> lstQuestionGen = new ArrayList<>();
        while (lstQuestionGen.size() < numberOfElementsToSelect) {
            int randomIndex = random.nextInt(myObjectList.size());
            Question randomElement = myObjectList.get(randomIndex);
             myObjectList.remove(randomIndex);
                lstQuestionGen.add(randomElement);
        }
        this.lstQuestion = lstQuestionGen;
    }
}

package com.example.gameapp.model;

public class Points {

    int pointTotal;

    public Points(){

        pointTotal = 0;
    }

    public int addPoints(int toAdd){
        pointTotal += toAdd;
        return pointTotal;
    }

    public int minusPoints(int toMinus){
        pointTotal -= toMinus;
        return pointTotal;
    }

    public int getTotal(){
        return pointTotal;
    }
}

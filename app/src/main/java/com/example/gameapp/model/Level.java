package com.example.gameapp.model;

import android.widget.Chronometer;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.gameapp.R;
import com.example.gameapp.model.cardcollections.PlayingPile;

import java.io.Console;
import java.io.Serializable;
import java.util.Queue;
import java.util.Stack;

import static java.lang.System.currentTimeMillis;


public class Level{



    Deck deck;
    public Player player1;
    public Player computer;
    PlayingPile playingPile;
    public final ObservableBoolean playersturn = new ObservableBoolean() {
        @Override
        public void set(boolean value){
            if(player1.hand.isEmpty()){
                super.set(false);
            }
            else if(computer.hand.isEmpty()){
                super.set(true);
            }
            else{
                super.set(value);
            }
        }
    };
    public long startTime;
    public long stopTime;
    public Stack<Long> times;
    public boolean playerWon; //To set hackily for debugging purposes
    public long playerDelay;

    public Level(){

        player1=new Player();
        computer= new Player();
        deck = new Deck();
        deck.deal(player1,computer);
        playingPile = new PlayingPile();
        playersturn.set(true);
        playerWon=false;
        startTime=0;
        stopTime=0;
        times =new Stack();
        playerDelay=0;

    }

    public boolean playerWon() {
        return playerWon||(player1.hand.size()==52); //Gameover and you have 52 cards
    }

    public boolean computerWon() {
        return(computer.hand.size()==52); //Gameover and computer have 52 cards
    }

    public boolean levelFinished() {
        return (playerWon()||computerWon()||playerWon);
    }

    public Card getTopCard(){
        return playingPile.getTopCard();
    }

    public void stopComputerTimer() {
        stopTime = currentTimeMillis();
        playerDelay = stopTime-startTime;
        /*if(times.size()>5){
            times.pop();
            times.add(playerDelay);
        }
        else times.add(playerDelay);
        */


    }

    public int getPlayerDelay() {
        if(startTime==0||stopTime==0){
            return 1500;
        }
        //If too long return a proportional delay to players delay but not very long.
        return (int)playerDelay<3000 ? (int)playerDelay: (int) (3000 + Math.sqrt((int) playerDelay));
    }

    public void startComputerTimer() {
        startTime = currentTimeMillis();


    }



    public int getCompTotal() {
        return computer.hand.size();
    }
    public int getPlayerTotal() {
        return player1.hand.size();
    }
    public int getPileTotal() {
        return playingPile.size();
    }

    //actions

    /*
    Perform Card actions
     */
    public boolean flipFromPlayerHand(){
        if (playersturn.get())
            if (!player1.hand.isEmpty())
                if (!levelFinished()) { //if can still flip, and has cards in hand, and is their turn
                    player1.hand.move(player1.hand.getTopCard(), playingPile);
                    endTurn();
                    return true;

                }
        return false;
    }

    public boolean flipFromComputerHand(){
        if (!playersturn.get())
            if (!computer.hand.isEmpty())
                if (!levelFinished()) { //if can still flip, and has cards in hand, and is their turn
                    computer.hand.move(computer.hand.getTopCard(), playingPile);
                    endTurn();
                    return true;
                }
        return false;
    }

    public boolean playerSnap(){
        resetDelay();
        if(!levelFinished()) {
            if (checkSnap()) { //valid snap - get cards
                winsCards("player");
                return true;
            } else {//invalidSnap - give cards to other player
                winsCards("computer");
                return false;
            }
        }
        return false;
    }
    public boolean computerSnap(){
        resetDelay();
        if(!levelFinished()) {
            if (checkSnap()) { //valid snap
                // - get cards
                winsCards("computer");
                return true;
            } else {//invalidSnap - give cards to other player
                winsCards("player");
                return false;
            }
        }
        return false;
    }

    public void resetDelay(){
        stopTime=0;
        startTime=0;
    }
    //chANGES TURN TO WHOEVER JUST WON THE CARDS
    public void winsCards(String player){
        switch(player){
            case "player":
                playingPile.moveAll(player1.hand);
                setTurn("player");
                break;
            case "computer":
                 playingPile.moveAll(computer.hand);
                 setTurn("computer");
                 break;
            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }


    public void setTurn(String player){
        switch (player){
            case "player": playersturn.set(true);
            break;
            case "computer": playersturn.set(false);
            break;
        }
    }
    public void endTurn(){
        if(playersturn.get()){
            playersturn.set(false);
        }
        else playersturn.set(true);
    }


    // could rewrite with rules?
    // If top card matches position
    // ToDo: If previous card matches current
    public boolean checkSnap(){
        Card card = playingPile.getTopCard();
        Card prevCard = playingPile.getPreviousCard();
        if(card==null){
            return false;
        }

        //if same number
        int cardValue = card.getLogicValue();
        int stackCount = playingPile.getPosition(); //Positions going from 1-13
        if(cardValue==stackCount){
            return true;
        }

        //if snap
        if(prevCard!=null) {
            int prevValue = prevCard.getLogicValue();
            if(cardValue==prevValue){
                return true;
            }
        }
        return false;
    }

    public void winLevel(){
        playerWon=true;
    }

}

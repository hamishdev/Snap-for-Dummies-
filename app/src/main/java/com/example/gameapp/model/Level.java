package com.example.gameapp.Model;

import com.example.gameapp.Model.CardCollections.CardList;
import com.example.gameapp.Model.CardCollections.PlayingPile;
import java.io.Serializable;


public class Level implements Serializable{


    Turn turn;
    Deck deck;
    Player player1;
    Player computer;
    PlayingPile playingPile;
    int levelNumber;
    public boolean playerWon; //To set hackily for debugging purposes

    public Level(int levelNumber){

        this.levelNumber = levelNumber;

        player1=new Player();
        computer= new Player();
        deck = new Deck();
        deck.deal(player1,computer);
        playingPile = new PlayingPile();
        
        turn = Turn.PLAYER;
        playerWon=false;

    }

    public boolean playerWon() {
        return(player1.hand.size()==52); //Gameover and you have 52 cards
    }

    public boolean computerWon() {
        return(computer.hand.size()==52); //Gameover and computer have 52 cards
    }

    public boolean levelFinished() {
        return (playerWon()||computerWon()||playerWon);
    }



    enum Turn{COMPUTER,PLAYER}

    //Just have players turn so that can only be binary player or Computers turn
    public boolean isPlayersTurn(){
        if(player1.hand.isEmpty()){
            return false;
        }
        if(computer.hand.isEmpty()){
            return true;
        }
        else
        return(turn.equals(Turn.PLAYER));
    }

    public int getCompTotal() {
        return player1.hand.size();
    }

    public int getPlayerTotal() {
        return computer.hand.size();
    }


    //actions

    /*
    Perform Card actions
     */
    public boolean flipFromPlayerHand(){
        if(!levelFinished()&&!player1.hand.isEmpty()&&isPlayersTurn()) { //if can still flip, and has cards in hand, and is their turn
           player1.hand.move(player1.hand.getTopCard(), playingPile);
           endTurn();
           return true;

        }
        return false;
    }

    public boolean flipFromComputerHand(){
        if(!levelFinished()&&!computer.hand.isEmpty()&&!isPlayersTurn()) { //if can still flip, and has cards in hand, and is their turn
            computer.hand.move(computer.hand.getTopCard(), playingPile);
            endTurn();
            return true;
        }
        return false;
    }

    public boolean playerSnap(){
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
            case "player": turn =Turn.PLAYER;
            break;
            case "computer": turn = Turn.COMPUTER;
            break;
        }
    }
    public void endTurn(){
        if(isPlayersTurn()){
            turn = Turn.COMPUTER;
        }
        else turn = Turn.PLAYER;
    }

    public Deck getDeck(){
        return deck;
    }


    // could rewrite with rules?
    // If top card matches position
    // ToDo: If previous card matches current
    public boolean checkSnap(){
        Card card = playingPile.getTopCard();
        Card prevCard = playingPile.getPreviousCard();
        if(card==null||prevCard==null){
            return false;
        }
        int stackCount = playingPile.getPosition(); //Positions going from 1-13

        int cardValue = card.value.ordinal()+1;
        int prevValue = prevCard.getLogicValue();
        if(cardValue==stackCount){
            return true;
        }
        if(cardValue==prevValue){
            return true;
        }
        else return false;
    }

}

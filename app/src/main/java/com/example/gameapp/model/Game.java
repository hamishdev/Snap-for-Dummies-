package com.example.gameapp.Model;

import java.io.Serializable;

public class Game implements Serializable {

    Level[] levels;
    Level currentLevel;
    int levelUpTo;
    public Game(){
        //levels.add
        levelUpTo=1;
    }

    //levelNumber will correlate to speed
    public boolean startLevel(int levelNumber) {
        if(levelUpTo>=levelNumber) {
            currentLevel = new Level(levelNumber);
            return true;
        }
        return false;

    }



    //Level Specific logic

    public boolean flipCard(String player) {
        switch (player) {
            case "player":
                return currentLevel.flipFromPlayerHand();

            case "computer":
                return currentLevel.flipFromComputerHand();

            default:
                return false;
        }
    }

    public Card getTopCard(){
        return currentLevel.playingPile.getTopCard();
    }
    public int getPlayerTotal(){ return currentLevel.player1.hand.size();}
    public int getComputerTotal(){ return currentLevel.computer.hand.size();}
    public int getPileTotal(){return currentLevel.playingPile.size();}

    public boolean snap(String player) {
        switch (player) {
            case "player":
                return currentLevel.playerSnap();

            case "computer":
                return currentLevel.computerSnap();

            default:
                return false;
        }
    }

    public boolean checkSnap(){
        return currentLevel.checkSnap();
    }

    public boolean computerTurn(){return !currentLevel.isPlayersTurn();}

    public boolean checkWon() {
        return currentLevel.levelFinished();
    }

    public boolean playerWon() {
        return currentLevel.playerWon();
    }
    public boolean computerWon() {
        return currentLevel.computerWon();
    }

    //If playing the current level to beat, and won, update levelUpTo
    public void finishedLevel(boolean playerWon) {
        if (playerWon && (currentLevel.levelNumber == levelUpTo)) {
            levelUpTo++;
        }
    }
}

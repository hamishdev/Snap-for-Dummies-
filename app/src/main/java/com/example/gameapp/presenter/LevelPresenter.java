package com.example.gameapp.presenter;

import com.example.gameapp.model.Card;
import com.example.gameapp.model.Level;
import com.example.gameapp.view.LevelsView;

import java.io.Serializable;

public class LevelPresenter implements Presenter, Serializable {

    private LevelsView view;
    private Level model;

    public LevelPresenter(LevelsView _view){
        this.view = _view;
        model = new Level();
    }


    // Update
    // playing pile
    // Player total
    public void onPlayerFlip(){
        if(model.flipFromPlayerHand()){
            updateOnCardFlipped();
            newTurn();
        }

    }

    private void newTurn() {
        view.computerSnapIfCan();
        if(!model.isPlayersTurn()){
            view.computerFlip();
        }
    }

    public void onComputerFlip() {
        if(model.flipFromComputerHand()) {
            updateOnCardFlipped();
            newTurn();
        }
    }

    /* Check if valid snap
    if valid
        checkwon
        update playing card
        update card totals
    if not valid
        checkwon
        update playing card
        update cardtotals
     */
    public void onPlayerSnap(){
        model.playerSnap();
        if(!model.levelFinished()){
            updateOnCardFlipped();
            newTurn();
        }
        else{
gameOver();}
    }

    public void computerSnapIfCan(){
        if(model.checkSnap()) {
            model.computerSnap();
            if (!model.levelFinished()) {
                updateOnCardFlipped();
                newTurn();
            } else {
                gameOver();

            }
        }

    }

    public void gameOver(){
        view.gameOver(model.playerWon());// boolean whether player has won
    }
    /*
    Gets top card from model, updates view,
    Gets cardTotals from model, updates view
    */
    public void updateOnCardFlipped(){
        Card topCard = model.getTopCard();
        view.setPlayingCard(topCard);
        view.setTotals(model.getPlayerTotal(),model.getCompTotal(),model.getPileTotal());
    }


    public void winLevel() {
        model.winLevel();
    }
}

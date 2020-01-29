package com.example.gameapp.Presenter;

import android.graphics.ColorSpace;

import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.Game;
import com.example.gameapp.View.SnapLevelsView;
import com.example.gameapp.View.levels.LevelsView;

import java.io.Serializable;

public class LevelPresenter implements Presenter, Serializable {

    private LevelsView view;
    private Game model;

    public LevelPresenter(LevelsView _view, Game _model){
        this.view = _view;
        this.model= _model;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    // Update
    // playing pile
    // Player total
    public void onPlayerFlip(){
        if(model.flipCard("player")){
            updateOnCardFlipped();
            newTurn();
        }

    }

    private void newTurn() {
        view.computerSnapIfCan();
        if(model.computerTurn()){
            view.computerFlip();
        }
    }

    public void onComputerFlip() {
        if(model.flipCard("computer")) {
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
        model.snap("player");
        if(!model.checkWon()){
            updateOnCardFlipped();
            newTurn();
        }
        else{
gameOver();}
    }

    public void computerSnapIfCan(){
        if(model.checkSnap()) {
            model.snap("computer");
            if (!model.checkWon()) {
                updateOnCardFlipped();
                newTurn();
            } else {
                gameOver();

            }
        }

    }

    public void gameOver(){
        view.gameOver(model.playerWon());// boolean whether player has won
        model.finishedLevel(model.playerWon());

    }
    /*
    Gets top card from model, updates view,
    Gets cardTotals from model, updates view
    */
    public void updateOnCardFlipped(){
        Card topCard = model.getTopCard();
        view.setPlayingCard(topCard);
        view.setTotals(model.getPlayerTotal(),model.getComputerTotal(),model.getPileTotal());
    }




}

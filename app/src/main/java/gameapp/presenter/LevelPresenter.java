package gameapp.presenter;

import gameapp.model.Card;
import gameapp.model.Level;
import gameapp.view.LevelsView;

import java.io.Serializable;

public class LevelPresenter implements Presenter, Serializable {

    private LevelsView view;
    private Level model;

    public LevelPresenter(LevelsView _view, Level level){
        this.view = _view;
        model = level;

    }

    // Update
    // playing pile
    // Player total
    public void onPlayerFlip(){
        if(model.flipFromPlayerHand()){
            updateOnCardFlipped();
            model.stopComputerTimer();
        }

    }

    public void onComputerFlip() {
        if(model.flipFromComputerHand()) {
            updateOnCardFlipped();
            model.startComputerTimer();
        }
    }

    public void newTurn() {
        //Computer tries to snap even when they flip
        if(model.checkSnap()) {
            view.computerSnapIfCan();
        }
        //Computer tries to flip even when they flip
        else if(!model.playersturn.get()){
            view.computerFlip(model.getPlayerDelay());
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
        }
        else{ gameOver(); }
    }

    public void computerSnapIfCan(){
        if(model.checkSnap()) {
            model.computerSnap();
            if (!model.levelFinished()) {
                updateOnCardFlipped();
            }
            else { gameOver(); }
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
        view.clearHandlers();
    }


    public void winLevel() {
        model.winLevel();
    }
}

package com.example.gameapp.Model;

public class Board {


    Turn turn;
    DeckController deckController;
    Deck deck;
    public boolean playerWon; //To set hackily for debugging purposes

    public Board(){
        deck = new Deck();
        deckController = new DeckController(deck);
        deckController.deck.deal();
        turn = Turn.PLAYER;
        playerWon=false;

    }

    public boolean playerWon() {
        return(deck.getPlayerHand().size()==52); //Gameover and you have 52 cards
    }

    public boolean computerWon() {
        return(deck.getComputerHand().size()==52); //Gameover and you have 52 cards
    }

    public boolean checkGameOver() {
        return (playerWon()||computerWon()||playerWon);
    }

    enum Turn{COMPUTER,PLAYER}

    //Just have players turn so that can only be binary player or Computers turn
    public boolean isPlayersTurn(){
        if(deck.getPlayerHand().isEmpty()){
            return false;
        }
        if(deck.getComputerHand().isEmpty()){
            return true;
        }
        else
        return(turn.equals(Turn.PLAYER));
    }

    public int getCompTotal() {
        return deck.computerHand.size();
    }

    public int getPlayerTotal() {
        return deck.playerHand.size();
    }


    //actions

    /*
    Perform Card actions
     */
    public void flipFromPlayerHand(){
        if(!checkGameOver()) {
            deck.playerHand.move(deck.playerHand.getTopCard(), deck.playingPile);
        }
    }

    public void flipFromComputerHand(){
        if(!checkGameOver()) {
            deck.computerHand.move(deck.computerHand.getTopCard(), deck.playingPile);
        }
    }

    public boolean playerSnap(){
        if(!checkGameOver()) {
            if (deck.checkSnap()) { //valid snap - get cards
                deck.playingPile.moveAll(deck.getPlayerHand());

                return true;
            } else {//invalidSnap - give cards to other player
                deck.playingPile.moveAll(deck.getComputerHand());

                return false;
            }
        }
        return false;
    }
    public boolean computerSnap(){
        if(!checkGameOver()) {
            if (deck.checkSnap()) { //valid snap
                // - get cards
                deck.playingPile.moveAll(deck.getComputerHand());

                return true;
            } else {//invalidSnap - give cards to other player
                deck.playingPile.moveAll(deck.getPlayerHand());

                return false;
            }
        }
        return false;
    }

    public void endTurn(){
        if(isPlayersTurn()){
            turn = Turn.COMPUTER;
        }
        else turn = Turn.PLAYER;
    }

    public Deck getDeck(){
        return deckController.deck;
    }
    public DeckController getDeckController(){
        return deckController;
    }



}

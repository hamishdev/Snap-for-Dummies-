package com.example.gameapp.Model;

//More for action stuff, snap logic can be in deck?
public class DeckController {

    Deck deck;
    DeckController(Deck _deck){
        deck = _deck;
    }




    /*
    Get Top Cards
     */

    public Card getPlayingPileTopCard(){
        Card referenceCard = deck.playingPile.getTopCard();
        return referenceCard;
    }

    public Card getPlayerHandTopCard(){

        Card referenceCard = deck.playerHand.getTopCard();
        return referenceCard;
    }

    public Card getComputerHandTopCard(){

        Card referenceCard = deck.computerHand.getTopCard();
        return referenceCard;
    }

    public Card getDiscardPileTopCard(){

        Card referenceCard = deck.discardPile.getTopCard();
        return referenceCard;
    }


    public Boolean handIsEmpty(){
        return deck.playerHand.isEmpty();
    }
}

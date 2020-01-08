package com.example.gameapp.Model;

import android.util.Log;

import com.example.gameapp.Model.CardCollections.CardList;
import com.example.gameapp.Model.CardCollections.PlayingPile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;

public class Deck extends CardList {

    public CardList untouchedDeck;
    
    PlayingPile playingPile;
    CardList playerHand;
    CardList computerHand;
    CardList discardPile;
    
    public Deck(){
        untouchedDeck = new CardList();
        
        playingPile = new PlayingPile();
        playerHand = new CardList();
        computerHand = new CardList();
        discardPile = new CardList();
        EnumSet.allOf(Card.Value.class).forEach(value -> EnumSet.allOf(Card.Suit.class).forEach(suit-> untouchedDeck.add(new Card(value,suit))));

    }

    public CardList getPlayerHand(){
        return playerHand;
    }
    public CardList getComputerHand(){
        return computerHand;
    }
    public CardList getPlayingPile(){
        return playingPile;
    }
    public CardList getFreshDeck() {return untouchedDeck; }

    //Assign cards from untouchedDeck to hands
    public void deal(){
        ArrayList<Card> toMove = new ArrayList<Card>();
        ArrayList<Card> toMove2 = new ArrayList<Card>();
        Collections.shuffle(untouchedDeck);
        //Log.i("visibility","hand1 total before deal="+playerHand.size());
        Boolean player1 =true;
        for (Card card:untouchedDeck) {
            if(player1) {
                toMove.add(card);
            }
            else{
                toMove2.add(card);}

            //Log.i("vis", "player 1"+player1+ card.value+","+card.suit);
            player1=!player1;
        }
        untouchedDeck.removeAll(toMove);
        untouchedDeck.removeAll(toMove2);

        playerHand.addAll(toMove);
        computerHand.addAll(toMove2);

        //Log.i("vis","hand1 total after deal="+playerHand.size());
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



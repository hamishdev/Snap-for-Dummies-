package com.example.gameapp.Model;

import android.util.Log;

import com.example.gameapp.Model.CardCollections.CardList;
import com.example.gameapp.Model.CardCollections.PlayingPile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;

public class Deck extends CardList implements Serializable {

    public CardList untouchedDeck;

    
    public Deck(){

        //createDeck of cards
        untouchedDeck = new CardList();
        EnumSet.allOf(Card.Value.class).forEach(value -> EnumSet.allOf(Card.Suit.class).forEach(suit-> untouchedDeck.add(new Card(value,suit))));

    }

    //Assign cards from untouchedDeck to hands
    public void deal(Player player1, Player computer){

        Collections.shuffle(untouchedDeck);

        Boolean player =true;
        for (Card card:untouchedDeck) {
            if(player) {
                player1.getCard(card);
            }
            else{
                computer.getCard(card);}
            player=!player;
        }
        untouchedDeck.clear();

    }

}



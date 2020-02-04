package com.example.gameapp.model;

import com.example.gameapp.model.cardcollections.CardList;

import java.io.Serializable;

public class Player implements Serializable {

    CardList hand;
    public Player(){
        hand = new CardList();
    }

    public void getCards(CardList cards){
        cards.moveAll(hand);
    }

    public void getCard(Card card){
        hand.add(card);
    }

}

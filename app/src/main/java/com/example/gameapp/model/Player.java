package com.example.gameapp.Model;

import com.example.gameapp.Model.CardCollections.CardList;

import java.io.Serializable;
import java.util.ArrayList;

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

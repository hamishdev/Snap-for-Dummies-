package com.example.gameapp.Model;

import java.io.Serializable;

public class Card implements Serializable {

    public enum Value{
        ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING
    }

    public enum Suit{
        Hearts,Spades,Diamonds,Clubs
    }

    Value value;
    Suit suit;

    public Card(Value _value, Suit _suit){
        value = _value;
        suit= _suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public int getLogicValue(){
        return value.ordinal() +1;
    }
}

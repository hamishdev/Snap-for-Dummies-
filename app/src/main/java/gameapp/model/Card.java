package gameapp.model;

import java.io.Serializable;

public class Card implements Serializable {

    public enum Value{
        A,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,J,Q,K
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

    public String getUiValue(){
        if(value.ordinal()==0){
            return value.toString();
        }
        if (0<value.ordinal()&&value.ordinal()<10){
            return String.valueOf(value.ordinal()+1);
        }
        return value.toString();
    }
}

package gameapp.model.cardcollections;

import gameapp.model.Card;

import java.io.Serializable;
import java.util.ArrayList;
public class CardList extends ArrayList<Card> implements Serializable {

    public Card getTopCard(){
        if(!this.isEmpty()){
            return this.get(0);
        }
        else {
            return null;
        }
    }

    public void move(Card card, CardList to){
        if(this.size()!=0){
            this.remove(card);
            to.add(0,card);
        }
    }

    public void moveAll(CardList to){
        to.addAll(this);
        this.clear();
    }

}

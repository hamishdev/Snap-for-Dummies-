package gameapp.model.cardcollections;

import gameapp.model.Card;

public class PlayingPile extends CardList {



    public int getPosition(){

        if(this.size()!=0) {
            int modPosition = Math.floorMod(this.size(), 13);
            if (modPosition == 0) {
                return 13;
            } else {
                return modPosition;
            }
        }
        return 0;
    }

    public Card getPreviousCard(){
        if(this.size()>1) {
            return this.get(1);
        }
        else return null;
    }

}

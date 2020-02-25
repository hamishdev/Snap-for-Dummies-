package gameapp.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import gameapp.R;
import gameapp.model.Card;

public class level2Activity extends baseLevelActivity{




    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

    }


    public void setPlayingCard(Card card){
        String word =null;
        if (card!=null){
        switch (card.getLogicValue()) {
            case 1:
                word = "a";
                break;
            case 2:
                word= "is";
                break;
            case 3:
                word ="the";
                break;
            case 4:
                word = "isnt";
                break;
            case 5:
                word = "likes";
                break;
            case 6:
                word = "coffee";
                break;
            case 7:
                word = "firefly";
                break;
            case 8:
                word = "destroys";
                break;
            case 9:
                word ="beautiful";
                break;
            case 10:
                word ="metropolis";
                break;
            case 11:
                word ="interesting";
                break;
            case 12:
                word ="sharpshooter";
                break;
            case 13:
                word = "revolutionary";
                break;

        }
            updateTextView(word+ getEmojiByUnicode(getSuit(card)), R.id.stack);
        }
        else{
            updateTextView(null,R.id.stack);
        }
    }
    public void computerSnapIfCan(){
        super.computerSnapIfCan(1200);

    }

}

package gameapp.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import gameapp.R;
import gameapp.model.Card;

public class level1Activity extends baseLevelActivity {





    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

    }




    public void setPlayingCard(Card card){
        if(card!=null) {
            int unicode = getSuit(card);
            updateTextView(String.valueOf(card.getUiValue())+ getEmojiByUnicode(unicode), R.id.stack);
        }
        else{
            updateTextView(null,R.id.stack);
        }
    }

    public void computerSnapIfCan(){
        super.computerSnapIfCan(1200);
    }





}

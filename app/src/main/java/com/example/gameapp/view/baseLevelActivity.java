package com.example.gameapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gameapp.model.Card;
import com.example.gameapp.presenter.LevelPresenter;
import com.example.gameapp.R;

public abstract class baseLevelActivity extends Activity implements LevelsView {



    LevelPresenter presenter;
    int levelNumber;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutResourceId());
        presenter = new LevelPresenter(this);

    }


    public void setPlayingCard(Card card){
        if(card!=null) {
            updateTextView(card.getValue() + " of " + card.getSuit(), R.id.stack);
        }
        else{
            updateTextView(null,R.id.stack);
        }
    }

    public void setTotals(int playerTotal, int computerTotal, int pileTotal){

        updateTextView(Integer.toString(playerTotal),R.id.playerPoints);
        updateTextView(Integer.toString(computerTotal),R.id.compPoints);
        updateTextView(Integer.toString(pileTotal),R.id.ordinal);

    }
    @Override
    public void gameOver(boolean playerWon) {
        if(playerWon){
            postWonMessage();
        }
        else{postLostMessage();}
        // Start NewActivity.class
        Intent data = new Intent();
//---set the data to pass back---
        data.putExtra("win",playerWon);
        setResult(RESULT_OK, data);
        finish();
    }

    public void postWonMessage(){
        updateTextView("You won!!!",R.id.computerHand);
        updateTextView("You won!!!",R.id.stack);
        updateTextView("You won!!!",R.id.compPoints);
    }

    public void postLostMessage(){
        updateTextView("You lost...",R.id.playerHand);
        updateTextView("You lost...",R.id.stack);
        updateTextView("You lost...",R.id.playerPoints);
    }


    private void updateTextView(String toThis, int viewID) {
        TextView textView = (TextView) findViewById(viewID);
        textView.setText(toThis);
    }
    protected abstract int getLayoutResourceId();




}


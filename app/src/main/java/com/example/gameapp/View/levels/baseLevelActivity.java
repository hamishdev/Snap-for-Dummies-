package com.example.gameapp.View.levels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.Game;
import com.example.gameapp.Model.Points;
import com.example.gameapp.Model.Level;
import com.example.gameapp.Presenter.LevelPresenter;
import com.example.gameapp.Presenter.Presenter;
import com.example.gameapp.R;
import com.example.gameapp.View.Main.MainActivity;
import com.example.gameapp.View.SnapLevelsActivity;

import java.io.Serializable;

public abstract class baseLevelActivity extends Activity implements LevelsView {


    Game model;
    LevelPresenter presenter;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutResourceId());
        getModel();
        presenter = new LevelPresenter(this, model);

    }

    private void getModel() {
        // Have to figure out how this works on all ways of getting to activity
            model = (Game)getIntent().getSerializableExtra("model");

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
        Intent myIntent = new Intent(baseLevelActivity.this,
                SnapLevelsActivity.class);
        startActivity(myIntent);
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


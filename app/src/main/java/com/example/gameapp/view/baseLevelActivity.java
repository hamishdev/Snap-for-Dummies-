package com.example.gameapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.example.gameapp.databinding.ActivityLevel1Binding;
import com.example.gameapp.model.Card;
import com.example.gameapp.model.Level;
import com.example.gameapp.model.cardcollections.Hand;
import com.example.gameapp.presenter.LevelPresenter;
import com.example.gameapp.R;

public abstract class baseLevelActivity extends Activity implements LevelsView {



    LevelPresenter presenter;
    Button snap_button;
    Button flipCard_button;
    Button winGame_button;
    Handler compFlipHandler;
    Handler compSnapHandler;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Level level = new Level();
        ActivityLevel1Binding binding = DataBindingUtil.setContentView(this,R.layout.activity_level_1);
        binding.setLevel(level);
        presenter = new LevelPresenter(this,level);

        compFlipHandler = new Handler();
        compSnapHandler = new Handler();

        snap_button = (Button) findViewById(R.id.snap);
        flipCard_button = (Button) findViewById(R.id.flip_card);
        winGame_button = (Button) findViewById(R.id.win_game);


        //BUTTON CLICKS
        snap_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //board.playerWon=true; //for debugging if want easy win
                presenter.onPlayerSnap();
                presenter.newTurn();
            }
        });

        flipCard_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                presenter.onPlayerFlip();
                presenter.newTurn();
            }
        });

        winGame_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                presenter.winLevel();
            }
        });

    }



    //COMPUTER AI

    public void computerSnapIfCan(int delay){
        compSnapHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                presenter.computerSnapIfCan();
                presenter.newTurn();
            }

        }, delay);
    }

    public void computerFlip(int delay){
        compFlipHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                presenter.onComputerFlip();
                presenter.newTurn();
            }

        }, delay);
    }

    public void clearHandlers(){
        compFlipHandler.removeCallbacksAndMessages(null);
        compSnapHandler.removeCallbacksAndMessages(null);

    }


    //UI LOGIC
    public int getSuit(Card card){
        int spadeUnicode =0x2660;
        int heartUnicode =0x2665;
        int diamondUnicode =0x2666;
        int clubUnicode=0x2663;
        int unicode = 0;
        switch(card.getSuit()){
            case Hearts:
                unicode = spadeUnicode;
                break;
            case Diamonds:
                unicode = diamondUnicode;
                break;
            case Clubs:
                unicode = clubUnicode;
                break;
            case Spades:
                unicode = heartUnicode;
                break;
        }
        return unicode;
    }


    public void setTotals(int playerTotal, int computerTotal, int pileTotal){

        updateTextView(Integer.toString(playerTotal),R.id.playerPoints);
        updateTextView(Integer.toString(computerTotal),R.id.compPoints);
        int remainder = pileTotal%13;
        switch (remainder){
            case 0:
                if(pileTotal==0){
                    updateTextView(null,R.id.ordinal);
                }
                else updateTextView("...", R.id.ordinal);
                break;
            case 1://intentional fallthrough
            case 2://intentional fallthrough
            case 3:
                updateTextView(Integer.toString(remainder)+"...", R.id.ordinal);
            break;
            default:
                updateTextView("...", R.id.ordinal);

        }
    }

    public void postWonMessage(){
        updateTextView("You won!!!",R.id.computerHand);
        updateTextView("You won!!!",R.id.stack);
        updateTextView("You won!!!",R.id.compPoints);
    }

    public void postLostMessage(){
        updateTextView("You lost...",R.id.stack);
        updateTextView("You lost...",R.id.playerPoints);
    }

    public void updateTextView(String toThis, int viewID) {
        TextView textView = (TextView) findViewById(viewID);
        textView.setText(toThis);
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }


    // ACTIVITY LOGIC

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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }



}


package com.example.gameapp.Controller.Levels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.example.gameapp.Controller.LevelsActivity;
import com.example.gameapp.Controller.MainActivity;
import com.example.gameapp.Controller.SplashScreenActivity;
import com.example.gameapp.Model.Board;
import com.example.gameapp.Model.DeckController;
import com.example.gameapp.R;

import java.util.concurrent.Executor;
import java.util.logging.Level;

public class level1Activity extends baseLevelActivity {


    Button snap_button;
    Button flipCard_button;
    int level;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutResourceId());
        level = 0;
        snap_button =(Button) findViewById(R.id.snap);
        flipCard_button =(Button) findViewById(R.id.flip_card);

        // do extra stuff on your resources, using findViewById on your layout_for_activity1
        // Capture button clicks

        snap_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //board.playerWon=true; //for debugging if want easy win
                board.playerSnap();
                updateEverything();



            }

        });

        flipCard_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                drawPlayerCard();
                level1Activity.super.board.endTurn();
                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        if(!board.isPlayersTurn()){
                            drawComputerCard();
                            board.endTurn();
                        }
                    }
                }.start();
            }
        });




        //computer flips when its their turn after 3 seconds
        //if snap
        // delay 3 seconds
        //computer snap
        Thread compThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        if (board.getDeck().checkSnap()){
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(board.getDeck().checkSnap()){
                                        board.computerSnap();
                                        updateEverything();
                                    }
                                }
                            });}

                    }
                } catch (InterruptedException e) {
                }}
        };

/*
Thread compFlipCardThread = new Thread(){
    @Override
    public void run() {
        try {
                while (!isInterrupted()) {
                    if (!board.isPlayersTurn()){
                        Thread.sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!board.isPlayersTurn()){
                                    drawComputerCard();
                                    board.endTurn();
                                }
                            }
                        });}

                }

            }catch (InterruptedException e) {
                }}
    };
*/

        compThread.start();
        //compFlipCardThread.start();

    }



    @Override
    public void checkWon(){
        //Update external activities like what level you can do?
        if(board.checkGameOver()) {
            if(board.playerWon()||board.playerWon) {
                level1Activity.super.postWonMessage();
                SharedPreferences mPrefs = getApplicationContext().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

                SharedPreferences.Editor ed = mPrefs.edit();
                ed.putBoolean("level1Completed", true);
                ed.commit();
            }
            else if (board.computerWon()){
                level1Activity.super.postLostMessage();
            }
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        sleep(2000);

                    } catch (Exception e) {
                        e.printStackTrace();

                    } finally {
                        // Start NewActivity.class
                        Intent myIntent = new Intent(level1Activity.this,
                                LevelsActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                }
            };
            thread.start();
        }
    }



    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_level_1;
    }


}

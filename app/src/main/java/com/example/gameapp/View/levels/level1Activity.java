package com.example.gameapp.View.levels;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.gameapp.R;

public class level1Activity extends baseLevelActivity {


    Button snap_button;
    Button flipCard_button;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutResourceId());

        snap_button = (Button) findViewById(R.id.snap);
        flipCard_button = (Button) findViewById(R.id.flip_card);

        // do extra stuff on your resources, using findViewById on your layout_for_activity1
        // Capture button clicks

        snap_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                //board.playerWon=true; //for debugging if want easy win
                presenter.onPlayerSnap();



            }

        });

        flipCard_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
               presenter.onPlayerFlip();
            }
        });
    }

    public void computerFlip(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                presenter.onComputerFlip();
            }

        }, 1500);
    }


    public void computerSnapIfCan(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                presenter.computerSnapIfCan();
            }

        }, 1000);
    }
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_level_1;
    }



}

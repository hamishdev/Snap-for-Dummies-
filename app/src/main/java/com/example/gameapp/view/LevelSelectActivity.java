package com.example.gameapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import androidx.databinding.DataBindingUtil;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;


import com.example.gameapp.databinding.ActivityLevelsSelectBinding;
import com.example.gameapp.model.Card;
import com.example.gameapp.model.Level;
import com.example.gameapp.model.LevelSelector;
import com.example.gameapp.presenter.LevelSelectPresenter;
import com.example.gameapp.R;
import com.github.jinatonic.confetti.CommonConfetti;

import java.util.ArrayList;

public class LevelSelectActivity extends Activity implements SnapLevelsView {


    LevelSelector model;
    LevelSelectPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("saveState",MODE_PRIVATE);
        int level = settings.getInt("level",0);
        if (level == 0) {
            //gameState = savedInstanceState.getString(GAME_STATE_KEY);
            model = new LevelSelector();
        }
        else{
            model = new LevelSelector(level);
        }


        presenter = new LevelSelectPresenter(this,model);
        ActivityLevelsSelectBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_levels_select);
        binding.setLevelSelector(model);
        // Locate the level1button in activity_main.xml

        ArrayList<Button> buttons = new ArrayList<Button>() {
            {
                add((Button)findViewById(R.id.Levels_1));
                add((Button)findViewById(R.id.Levels_2));
                add((Button)findViewById(R.id.Levels_3));
                //add((Button)findViewById(R.id.Levels_12));

            }

        };

        for(Button button :buttons){
            // Capture level1button clicks
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    onLevelClicked(Integer.parseInt((String)button.getTag()));

                }
            });
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                presenter.wonGame();
            }
        }, 200);


    }
    protected void onPause()
    {
        super.onPause();

        // Store values between instances here
        SharedPreferences preferences = getSharedPreferences("saveState",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();  // Put the values from the UI
        int level = model.levelUpTo.get();

        editor.putInt("level", level);
        // Commit to storage
        editor.commit();
    }


    public void onLevelClicked(int levelNumber){

        presenter.levelSelected(levelNumber);

    }

    static final int START_LEVEL =1;

    public void startLevel(int levelNumber){
        Intent levelIntent = getLevelIntent(levelNumber);
        startActivityForResult(levelIntent,START_LEVEL);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == START_LEVEL) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                boolean win = data.getBooleanExtra("win",false);
                presenter.levelEnded(win);
            }
        }
    }
    private Intent getLevelIntent(int levelNumber) {
        switch(levelNumber){
            case 1:
                return new Intent(LevelSelectActivity.this,
                        level1Activity.class);
            case 2:
                return new Intent(LevelSelectActivity.this,
                        level2Activity.class);
            case 3:
                return new Intent(LevelSelectActivity.this, level3Activity.class);

        }
        return null;
    }

    public void postConfetti(){
        int[] colorArray = new int[]{
                ContextCompat.getColor(this, R.color.gold),
                ContextCompat.getColor(this, R.color.gold_dark),
                ContextCompat.getColor(this, R.color.gold_light)
        };
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup container = findViewById(R.id.levelselect_view);
        CommonConfetti.rainingConfetti(container,colorArray).infinite();

    }


}
